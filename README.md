# Spring Boot JPA 的演示代码


## jpa_mysql

一个最简单的spring boot jpa 演示工程，可以通过rest的方式访问


知识点：

    1. 如何通过 @Modifying @Query 来自定义SQL语句更新删除数据
    
    因为更新涉及的事务，所以必须加@Transcational, 默认的MySQL 是使用Isam数据表，ISAM是不支持事务的，所以需要在application.yml加入以下配置来
    打开事务支持
    
    spring:
        jpa:
            database-platform: org.hibernate.dialect.MySQL5InnoDBDialect #InnoDB才能支持事务
            
    加上上面的配置之后，@Transcational 就可以使用了，详细查看ProductRepository的 updateNameAndPrice