package com.sang.cadastroaplicacao.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sang.cadastroaplicacao.models.ToItem;
import com.sang.cadastroaplicacao.repositories.ToItemRepository;

@Component
public class ToItemDataLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(ToItemDataLoader.class);


    @Autowired 
    ToItemRepository todaItemRepository;
    

    @Override

    public void run(String... args) throws Exception{
        loadData();
    }

    private void   loadData(){
        if(todaItemRepository.count()==0){
            ToItem toItem1 = new ToItem("pegar uma laranja");
            ToItem toItem2 = new ToItem("pegar uma labacate");

            todaItemRepository.save(toItem1);
            todaItemRepository.save(toItem2);

        }

        logger.info("Numero de produtos: {}", todaItemRepository.count());
    }
}
