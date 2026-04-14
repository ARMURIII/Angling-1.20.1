package com.eightsidedsquare.angling.common.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;

import java.util.function.Predicate;

public class ConditionalTask<E extends LivingEntity> implements Task<E> {

    private final Predicate<E> predicate;
    private final Task<? super E> task;

    public ConditionalTask(Predicate<E> predicate, Task<? super E> task) {
        this.predicate = predicate;
        this.task = task;
    }

    @Override
    public MultiTickTask.Status getStatus() {
        return task.getStatus();
    }

    @Override
    public boolean tryStarting(ServerWorld world, E entity, long time) {
        return predicate.test(entity) && task.tryStarting(world, entity, time);
    }

    @Override
    public void tick(ServerWorld world, E entity, long time) {
        task.tick(world,entity,time);
    }

    @Override
    public void stop(ServerWorld world, E entity, long time) {
        task.stop(world,entity,time);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
