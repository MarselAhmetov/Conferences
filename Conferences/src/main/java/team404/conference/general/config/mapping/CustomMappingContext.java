package team404.conference.general.config.mapping;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomMappingContext extends MappingContext {

    public static final String STACK = "customMappingContext_Stack";

    public CustomMappingContext(Map<Object, Object> globalProperties) {
        super(globalProperties);
    }

    @Override
    public void beginMapping(Type<?> sourceType, Object source, Type<?> destType, Object dest) {
        Stack<Item> stack = (Stack<Item>) getProperty(STACK);
        if (Objects.isNull(stack)) {
            stack = new Stack<>();
            setProperty(STACK, stack);
        }
        stack.push(new Item(sourceType, source));
        super.beginMapping(sourceType, source, destType, dest);
    }

    @Override
    public void endMapping() {
        Stack<Item> stack = (Stack<Item>) getProperty(STACK);
        if (stack != null) {
            stack.pop();
        }
        super.endMapping();
    }

    public static class Factory extends MappingContext.Factory {

        LinkedBlockingQueue<MappingContext> contextQueue = new LinkedBlockingQueue<>();
        ConcurrentHashMap<Object, Object> globalProperties = new ConcurrentHashMap<Object, Object>();

        public MappingContext getContext() {
            CustomMappingContext context = (CustomMappingContext) contextQueue.poll();
            if (context == null) {
                context = new CustomMappingContext(globalProperties);
            }
            context.containsCycle = true;
            return context;
        }

        public void release(MappingContext context) {
            context.reset();
            contextQueue.offer(context);
        }
        public Map<Object, Object> getGlobalProperties() {
            return globalProperties;
        }
    }

    public static class Item {
        private Type<?> sourceType;
        private Object source;

        public Item(Type<?> sourceType, Object source) {
            this.sourceType = sourceType;
            this.source = source;
        }

        public Type<?> getSourceType() {
            return sourceType;
        }

        public Object getSource() {
            return source;
        }
    }
}

