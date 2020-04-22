package com.momo.springboot.database.main;


import com.momo.springboot.database.first.First;
import com.momo.springboot.database.first.repository.FirstRepository;
import com.momo.springboot.database.second.Second;
import com.momo.springboot.database.second.repository.SecondRepository;
import com.momo.springboot.database.third.repository.ThirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemoService {

    @Autowired
    private FirstRepository firstRepository;
    @Autowired
    private SecondRepository secondRepository;
    @Autowired
    private ThirdRepository thirdRepository;

    // secondTransactionManager 事务不生效，这种情况可以引入JTA
    @Transactional
    public void save(int i) {
        First first = new First();
        first.setName("first");
        firstRepository.save(first);

        Second second = new Second();
        second.setCode("second");
        secondRepository.save(second);

        int j = 1 / i;
    }

    @Transactional(value = "secondTransactionManager", rollbackFor = Exception.class)
    public void delete_second(String id, int state) {
        secondRepository.delBySql(id);
        int i = 1 / state;

    }
    @Transactional(value = "secondTransactionManager", rollbackFor = Exception.class)
    public void updateByHql_first(String id, String name, int state) {
        firstRepository.updateByHql(id, name);
        int i = 1 / state;
    }

    @Transactional(value = "secondTransactionManager", rollbackFor = Exception.class)
    public void update_second(String id, String code, int state) {
        secondRepository.updateBySql(id, code);
        int i = 1 / state;

    }


    public void find() {
        List<First> firsts = firstRepository.findAll();
        System.out.println(firsts.get(0).getName());

        List<Second> seconds = secondRepository.findAll();
        System.out.println(seconds.get(0).getCode());
    }

    @Transactional
    public void saveFirst(int j) {
        First first = new First();
        first.setName("first");
        firstRepository.saveByEntity(first);

        int i = 1 / j;
    }

    @Transactional(value = "secondTransactionManager", rollbackFor = Exception.class)
    public void saveSecond(int j) {
        Second second = new Second();
        second.setCode("second");
        secondRepository.saveByEntity(second);

        int i = 1 / j;
    }

    // @Transactional
    @Transactional(value = "thirdTransactionManager", rollbackFor = Exception.class)
    public void saveThird(int j) {
        thirdRepository.save("1", "man");

        int i = 1 / j;
    }


}
