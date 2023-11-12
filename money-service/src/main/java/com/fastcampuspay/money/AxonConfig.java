package com.fastcampuspay.money;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

   /* @Bean
    public EventStore eventStore(EventStorageEngine storageEngine,
                                 GlobalMetricRegistry metricRegistry) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(metricRegistry.registerEventBus("eventStore"))
                .build();
    }*/

  /*  @Bean
    public EventStorageEngine eventStorageEngine(Serializer serializer,
                                                 PersistenceExceptionResolver persistenceExceptionResolver,
                                                 @Qualifier("eventSerializer") Serializer eventSerializer,
                                                 EntityManagerProvider entityManagerProvider,
                                                 TransactionManager transactionManager) {
        return JpaEventStorageEngine.builder()
                .snapshotSerializer(serializer)
                .persistenceExceptionResolver(persistenceExceptionResolver)
                .eventSerializer(eventSerializer)
                .entityManagerProvider(entityManagerProvider)
                .transactionManager(transactionManager)
                .build();
    }*/

    /*@Bean
    SimpleCommandBus commandBus(TransactionManager transactionManager){
        return  SimpleCommandBus.builder().transactionManager(transactionManager).build();
    }
    @Bean
    public AggregateFactory<MemberMoneyAggregate> aggregateFactory(){
        return new GenericAggregateFactory<>(MemberMoneyAggregate.class);
    }
    @Bean
    public Snapshotter snapshotter(EventStore eventStore, TransactionManager transactionManager){
        return AggregateSnapshotter
                .builder()
                    .eventStore(eventStore)
                    .aggregateFactories(aggregateFactory())
                    .transactionManager(transactionManager)
                .build();
    }
    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(EventStore eventStore, TransactionManager transactionManager){
        final int SNAPSHOT_TRHRESHOLD = 5;
        return new EventCountSnapshotTriggerDefinition(snapshotter(eventStore,transactionManager),SNAPSHOT_TRHRESHOLD);
    }

    @Bean
    public Repository<MemberMoneyAggregate> accountAggregateRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTriggerDefinition){
        return EventSourcingRepository
                .builder(MemberMoneyAggregate.class)
                    .eventStore(eventStore)
                    .snapshotTriggerDefinition(snapshotTriggerDefinition)
                .build();
    }

    @Bean
    public Cache cache(){
        return new WeakReferenceCache();
    }

    @Bean
    public Repository<MemberMoneyAggregate> accountAggregateRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTriggerDefinition, Cache cache){
        return CachingEventSourcingRepository
                .builder(MemberMoneyAggregate.class)
                .eventStore(eventStore)
                .snapshotTriggerDefinition(snapshotTriggerDefinition)
                .cache(cache)
                .build();
    }*/
}