package com.keep.utils;



import com.alibaba.fastjson.JSON;
import com.keep.entity.*;
import com.keep.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class inherAlg {
    @Autowired
    private UserMapper mapper;
    private int Variance;
    private final List<List<List<shopFlow>>> list = new ArrayList<>();
    private final Map<String, List<employee>> empData = new HashMap<>();
    private final Map<String, Double> selfRule = new HashMap<>();
    private final Map<String, Integer> fixedRule = new HashMap<>();
    private final Map<String, Map<String, Integer>> dayPre = new HashMap<>();
    private final Map<String, Map<String, Integer>> timePre = new HashMap<>();
    private final Map<String, Integer> groupPre = new HashMap<>();
    private Shop shopInformation;

    private void init(employee ep) throws IOException {
        shopInformation = mapper.selectShop(ep.getID());
        String shopId = shopInformation.getID();

        Rule rule = mapper.selectRule(shopId);
        Map self = JSON.parseObject(rule.getSelfRule());
        Map fixed = JSON.parseObject(rule.getFixedRule());
        for(Object obj : fixed.keySet()) {
            Integer data = (Integer)fixed.get(obj);
            String str = (String)obj;
            fixedRule.put(str, data);
        }
        for(Object obj : self.keySet()) {
            String data = String.valueOf(self.get(obj));
            Double dou = Double.valueOf(data);
            String str = (String) obj;
            selfRule.put(str, dou);
        }
        for(int i = 1 ; i <= 4 ; i++) {
            List<List<shopFlow>> lrt = new ArrayList<>();
            for(int j = 1;j<=7;j++) {
                List<shopFlow> lst = mapper.selectSF(shopId, i, j);
                lrt.add(lst);
            }
            list.add(lrt);
        }
        List<employee> empD = mapper.selectEmployeeData(shopId);  // 搜索该门店员工的信息
        for(employee em : empD) {
            employee E = new employee(em.getID(), em.getEmpName(), em.getEmail(), em.getPw(), em.getJob(), em.getEmpShopID());
            String job = em.getJob();
            List<employee> ls = empData.computeIfAbsent(job, k -> new ArrayList<>());
            ls.add(E);
            empData.put(job, ls);
        }
        List <empPre> empList = mapper.selectEmpPre(shopId);
        for(empPre emppre : empList) {
            String empId = emppre.getEmpId();
            groupPre.put(empId, emppre.getGroupPre());
            Map empDayPre = JSON.parseObject(emppre.getDayPre());
            Map<String, Integer> dayEmp = new HashMap<>();
            for(Object dayKey : empDayPre.keySet()) {
                Integer day = (Integer) empDayPre.get(dayKey);
                String dKey = (String) dayKey;
                dayEmp.put(dKey, day);
            }
            dayPre.put(empId, dayEmp);

            Map<String, Integer> empTime = new HashMap<>();
            Map empTimePre = JSON.parseObject(emppre.getTimePre());

            for(Object timeKey : empTimePre.keySet()) {
                Integer time = (Integer) empTimePre.get(timeKey);
                String timeK = (String) timeKey;
                empTime.put(timeK, time);
            }
            timePre.put(empId, empTime);

        }
    }

    public void algorithm(employee ep) {
        List<List<List<arrange>>> monthArrange = new ArrayList<>();
        try {
            init(ep);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int weekOfMonth = 0;
        int avg;
        final int empNumber = empData.get("店员").size();

        int stop ;
        final int maxSel = empNumber * empNumber + 200;
        final int numberOfTimes = 22010;
        for(List<List<shopFlow>> empList : list) {
            ++weekOfMonth;
            List<List<arrange>>weekArrange = new ArrayList<>();
            int dayOfWeek = 0;
            for(List<shopFlow> flowList : empList) {
                List<arrange> dayArrange = new ArrayList<>();
                ++dayOfWeek;
                int div = empNumber;
                boolean []vis = new boolean[empNumber+1];    // 判断员工是否愿意在今天工作
                for(int i = 0;i< empNumber ; i++) {
                    vis[i] = true;
                    employee empNeed = empData.get("店员").get(i);
                    for (String strKey : dayPre.get(empNeed.getID()).keySet()) {
                        if (dayOfWeek == dayPre.get(empNeed.getID()).get(strKey)) {
                            vis[i] = false;
                            div--;
                        }
                    }
                }

                int startTimeOfToday;
                int endTimeOfToday;
                if(dayOfWeek <= 5) {
                    startTimeOfToday = fixedRule.get("ordstart");
                    endTimeOfToday = fixedRule.get("ordend");
                }
                else {
                    startTimeOfToday = fixedRule.get("weekendstart");
                    endTimeOfToday = fixedRule.get("weekendend");
                }
                // 添加一个小组长
                dayArrange.add(makeArrange(selectGroup(dayOfWeek,startTimeOfToday - selfRule.get("advancetime").intValue(), startTimeOfToday ), "开店准备", startTimeOfToday - selfRule.get("advancetime").intValue(), startTimeOfToday, weekOfMonth, dayOfWeek));
                final int numOfEmp = (int)(shopInformation.getSize() / selfRule.get("openrule"));
                int numOfNow = 0;
                stop = 0;
                boolean []initResEmp = new boolean[empNumber+1];
                for(int i = 0 ; i < empNumber ; i++) {
                    initResEmp[i] = true;
                }
                // 添加组员
                do {
                    stop ++;
                    int index = rand(empNumber);
                    if (vis[index] && decide(empData.get("店员").get(index).getID(), startTimeOfToday - selfRule.get("advancetime").intValue(), startTimeOfToday)) {
                        ++numOfNow;
                        initResEmp[index] = false;
                        dayArrange.add(makeArrange(empData.get("店员").get(index), "开店准备", startTimeOfToday - selfRule.get("advancetime").intValue(), startTimeOfToday, weekOfMonth, dayOfWeek));
                    }
                } while (numOfEmp != numOfNow && stop < maxSel);

                int []needNum = new int[flowList.size()+1];
                int allTime = 0;
                int id = 0;
                List<List<Integer>> EmpArrangeData = new ArrayList<>();
                for(shopFlow sf : flowList) {                 // 统计每个班次需要的人数
                    List<Integer> tot = new ArrayList<>();
                    for(int k = 0; k<empNumber ; k++) {
                        tot.add(0);
                    }
                    EmpArrangeData.add(tot);
                    Double cusRule = selfRule.get("customerrule");
                    int defaultNum = selfRule.get("defaultnum").intValue();
                    double temp = sf.getCusFlow()/cusRule;
                    int res = (int) temp + defaultNum;
                    needNum[id++] = res;
                    allTime += res;
                }
                // 班次时长统计
                List<List<Integer>> timeLong = new ArrayList<>();
                for(int i=0 ; i < empNumber ; i++) {
                    List<Integer> tr = new ArrayList<>();
                    for(int j = 0 ; j < id ; j ++) {
                        tr.add(0);
                    }
                    timeLong.add(tr);
                }
                allTime *= 30;
                avg = allTime / div;
                for(int i = 0; i < id ; i++ ) {
                    int sum = 0;
                    int bool = 0;
                    do {
                        ++bool;
                        int rNum = rand(empNumber);
                        if(EmpArrangeData.get(i).get(rNum) == 1 || !vis[rNum] || !decide(empData.get("店员").get(rNum).getID(), flowList.get(i).getStartTime(), flowList.get(i).getEndTime())) {
                            continue;
                        }
                        boolean f1 = countTimeLong(timeLong, i, rNum, id, empData.get("店员").get(rNum).getID());
                        if(!f1 || (i == 0 && !initResEmp[rNum]))
                            continue;
                        timeLong.get(rNum).set(i, 1);
                        EmpArrangeData.get(i).set(rNum, 1);
                        ++sum;

                    }while (sum != needNum[i] && bool < maxSel * 4);
                }
                int []empWorkTime = new int[empNumber + 1];
                for(int i = 0 ; i < empNumber ; i++) {
                    empWorkTime[i] = 0;
                }
                for(int i = 0;i < id; i++) {                // 统计员工的工作时间
                    for(int j = 0; j < empNumber ;j++) {
                        if(EmpArrangeData.get(i).get(j) == 1) {
                            empWorkTime[j] += 30;
                        }
                    }
                }
                for(int i = 0;i < empNumber ; i++) {       // 初始化函数值
                    if(vis[i])
                        Variance += (empWorkTime[i] - avg) * (empWorkTime[i] - avg);
                }

                System.out.println("初始函数值： " + weekOfMonth + " : " + dayOfWeek + " : " + Variance);

                for(int i = 0;i < numberOfTimes ;i++) {
                    int fa = rand(id);
                    int ma = rand(id);
                    int mid = rand(empNumber);
                    List<Integer> son = new ArrayList<>();
                    for(int j = 0 ;j<mid; j++) {    // 交叉
                        son.add(EmpArrangeData.get(fa).get(j));
                    }
                    for(int j = mid ;j < empNumber; j++) {
                        son.add(EmpArrangeData.get(ma).get(j));
                    }
                    for(int j = 0;j < empNumber ; j++) {  // 变异
                        double imp = Math.random();
                        if(imp < 0.05) {
                            son.set(j , son.get(j) ^ 1);
                        }
                    }
                    for(int j = 0;j < empNumber ; j++) {  // 根据日偏好调整
                        if (son.get(j) == 1 && !vis[j]) {
                            son.set(j, 0);
                        }
                    }
                    int allData = 0;
                    for(int j = 0; j < empNumber ;j ++) {
                       allData += son.get(j);
                    }
                    // 优胜劣汰
                    int temVar = Variance;
                    int k = -1;
                    for(int j = 0 ;j <id ; j++ ) {
                        for(int s = 0 ; s < empNumber ; s ++) {  // 调整子代使其更适应环境
                            if(son.get(s) == 1) {
                                boolean v = countTimeLong(timeLong, j, s, id, empData.get("店员").get(s).getID());
                                boolean fl = decide(empData.get("店员").get(s).getID(), flowList.get(j).getStartTime(), flowList.get(j).getEndTime());
                                if(!v || !fl) {
                                    son.set(s, 0);
                                    allData--;
                                }
                            }
                        }
                        if(allData == needNum[j]) {
                            int total = 0;
                            for(int s = 0 ; s < empNumber ; s++) {    // 计算时间
                                int res = empWorkTime[s] - EmpArrangeData.get(j).get(s) * 30 + son.get(s) * 30;
                                total += (res - avg) * (res - avg);
                            }
                            if(temVar > total) {
                                k = j;
                                temVar = total;
                            }
                        }
                    }
                    if(k != -1) {
                        for(int j = 0 ;j < empNumber ;j++) {
                            empWorkTime[j] = empWorkTime[j] - EmpArrangeData.get(k).get(j) * 30 + son.get(j) * 30;
                            EmpArrangeData.get(k).set(j, son.get(j));
                        }
                        Variance = temVar;
                    }
                }
                System.out.println("优化后的函数值： " + weekOfMonth + " : " + dayOfWeek + " : " + Variance);
                // 生成一天的排班信息
                for(int i = 0; i < id;i ++) {
                    int startTime = flowList.get(i).getStartTime();
                    int endTime = flowList.get(i).getEndTime();
                    if(needNum[i] >= selfRule.get("num3").intValue()) {
                        dayArrange.add(makeArrange(empData.get("门店经理").get(0), "值班", startTime, endTime,weekOfMonth, dayOfWeek));
                    }
                    if(needNum[i] >= selfRule.get("num2").intValue()) {
                        while (true) {
                            int t = rand(empData.get("副经理").size());
                            boolean f = decide(empData.get("副经理").get(t).getID(), startTime, endTime);
                            for(String strKey : dayPre.get(empData.get("副经理").get(t).getID()).keySet()) {
                                if(dayOfWeek == dayPre.get(empData.get("副经理").get(t).getID()).get(strKey)) {
                                    f = false;
                                }
                            }
                            if(f) {
                                dayArrange.add(makeArrange(empData.get("副经理").get(t), "值班", startTime, endTime, weekOfMonth, dayOfWeek));
                                break;
                            }
                        }
                    }
                    if(needNum[i] >= selfRule.get("num1").intValue()){
                        dayArrange.add(makeArrange(selectGroup(dayOfWeek, startTime, endTime), "值班", startTime, endTime, weekOfMonth, dayOfWeek));
                    }
                    int realNum = 0;
                    for(int j = 0 ;j < empNumber ;j ++) {
                        if(EmpArrangeData.get(i).get(j) == 1) {
                            ++realNum;
                            dayArrange.add(makeArrange(empData.get("店员").get(j), "值班", startTime, endTime, weekOfMonth, dayOfWeek));
                        }
                    }
                    for(int j = realNum; j < needNum[i]; j++) {
                        dayArrange.add(new arrange(-1,"+",  "+", "+", "自由班次", shopInformation.getID(), startTime, endTime, weekOfMonth, dayOfWeek));
                    }
                }
                // 收尾工作人员安排
                dayArrange.add(makeArrange(selectGroup(dayOfWeek,endTimeOfToday, endTimeOfToday + selfRule.get("closetime").intValue()), "收尾工作", endTimeOfToday, endTimeOfToday + selfRule.get("closetime").intValue(), weekOfMonth, dayOfWeek));

                final int endNum = (int)(shopInformation.getSize() / selfRule.get("closerule"));
                int numOf = 0;
                int vr = 0;
                do {
                    int index = rand(empNumber);
                    ++vr;
                    if(timeLong.get(index).get(id - 1 ) == 1)
                        continue;
                    if (vis[index] && decide(empData.get("店员").get(index).getID(), endTimeOfToday, endTimeOfToday + selfRule.get("closetime").intValue())) {
                        ++numOf;
                        dayArrange.add(makeArrange(empData.get("店员").get(index), "收尾工作", endTimeOfToday, endTimeOfToday + selfRule.get("closetime").intValue(), weekOfMonth, dayOfWeek));
                    }
                } while (endNum != numOf && vr < maxSel);

                weekArrange.add(dayArrange);
            }
            monthArrange.add(weekArrange);
        }
        mapper.insertArrange(monthArrange);

        mapper.updateArranged(shopInformation.getID(), "true");
    }

    private int rand(int EmpNum) {
        return (int) (Math.random() * EmpNum);
    }

    private employee selectGroup(int day, int st, int et) {
        int groupNumber = empData.get("小组长").size();
        while(true) {
            int index = rand(groupNumber);
            employee groupEmp = empData.get("小组长").get(index);
            boolean flag = decide(groupEmp.getID(), st, et);
            for(String strKey : dayPre.get(groupEmp.getID()).keySet()) {
                if(day == dayPre.get(groupEmp.getID()).get(strKey)) {
                    flag = false;
                }
            }
            if(flag) {
                return groupEmp;
            }
        }
    }

    private boolean decide(String ID, int st, int et) {
        if (!timePre.get(ID).isEmpty()) {
            int s1 = timePre.get(ID).get("s1");
            int e1 = timePre.get(ID).get("e1");
            return s1 <= st && et <= e1;
        }
        return true;
    }
    private boolean countTimeLong(List<List<Integer>>timeLong, int i, int rNum,int sum, String id) {
        int allT = 1;
        for(int j = 0 ; j < sum ; j ++) {
            if (timeLong.get(rNum).get(j) == 1) {
                allT++;
            }
        }
        allT *= 30;
        if(allT > fixedRule.get("daymaxtime"))
            return false;
        int tLong = 1;
        for(int j = i - 1; j >= 0 ; j-- ) {
            if(timeLong.get(rNum).get(j) == 0) {
                break;
            }
            tLong++;
        }
        for(int j = i + 1; j < sum ;j++ ) {
            if(timeLong.get(rNum).get(j) == 0) {
                break;
            }
            tLong++;
        }
        tLong *= 30;
        int t = groupPre.get(id) * 60;
        return t >= tLong;
    }
    private arrange makeArrange(employee NeedEmp, String task, int startTime, int endTime, int week, int day) {
        return new arrange(-1, NeedEmp.getID(), NeedEmp.getEmpName(), NeedEmp.getJob(), task, shopInformation.getID(), startTime, endTime, week, day);
    }
}
