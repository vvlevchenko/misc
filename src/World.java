public class World {
    class Entity {
        protected final boolean isAnimal;
        protected boolean isExists;

        Entity(boolean isAnimal, boolean isExists) {
            this.isAnimal = isAnimal;
            this.isExists = isExists;
        }
    }

    class Animal extends Entity {
        Animal(boolean isExists) {
            super(true, isExists);
        }
    }

    class Plant extends Entity {
        Plant(boolean isExists) {
            super(false, isExists);
        }
    }


    class Dog extends Animal {
        Dog(boolean isExists) {
            super(isExists);
        }
    }
}

class World2{
    class Entity {
        protected final boolean isAnimal;
        protected boolean isExists = false;
        Entity(boolean isAnimal) {
            this.isAnimal = isAnimal;
        }

        protected void startLife(){
            isExists = true;
        }

        protected void endLife() {
            isExists = false;
        }
    }

    class Animal extends Entity{
        Animal(){
            super(true);
        }
    }

    class Plant extends Entity{
        Plant(){
            super(false);
        }
    }


    class Dog extends Animal{

    }
}

class World3 {
    enum LIFECYCLE{
        NOT_BORN,
        LIFE,
        DEAD
    }
    class Entity {
        private final boolean isAnimal;
        private boolean isExists;
        private LIFECYCLE lifecycle = LIFECYCLE.NOT_BORN;

        Entity(boolean isAnimal, boolean isExists) {
            this.isAnimal = isAnimal;
            this.isExists = isExists;
        }
        protected void startLife(){
            lifecycle = LIFECYCLE.LIFE;
        }

        protected void endLife() {
            lifecycle = LIFECYCLE.DEAD;
        }

        protected boolean isAlive() {
            return lifecycle == LIFECYCLE.LIFE;
        }

        final boolean isAnimal() {
            return isAnimal;
        }
    }

    class Animal extends Entity {
        Animal(boolean isExists) {
            super(true, isExists);
        }
    }

    class Plant extends Entity {
        Plant(boolean isExists) {
            super(false, isExists);
        }
    }


    class Dog extends Animal {
        Dog(boolean isExists) {
            super(isExists);
        }
    }

    void foo(Entity a) {
        if (a.isAnimal()) {
            // soem
        }

    }
}
/*
class WorldN{
    interface Entity {
        boolean isAnimal();
        boolean isExists();
    }
    class EntityImpl  implements Entity {
        protected final boolean isAnimal;
        protected boolean isExists = false;
        EntityImpl(boolean isAnimal) {
            this.isAnimal = isAnimal;
        }

        protected void startLife(){
            isExists = true;
        }

        protected void endLife() {
            isExists = false;
        }

        @Override
        public boolean isAnimal() {
            return isAnimal;
        }

        @Override
        public boolean isExists() {
            return isExists;
        }
    }

    class Animal extends EntityImpl{
        Animal(){
            super(true);
        }
    }

    class Plant extends EntityImpl{
        Plant(){
            super(false);
        }
    }

    class Dog extends Animal{}

    // Other hierarchy
    class EntityNetworkImpl  implements Entity {
        protected void startLife(){
            // change isExists on the server
        }

        protected void endLife() {
            // change isExists on the server
        }

        @Override
        public boolean isAnimal() {
            return ;
        }

        @Override
        public boolean isExists() {
            return isExists;
        }
    }

    class Animal extends EntityImpl{
        Animal(){
            super(true);
        }
    }

    class Plant extends EntityImpl{
        Plant(){
            super(false);
        }
    }

    class Dog extends Animal{}
}
 */


class Test{
    int foo(Ints ints) {
        return ints.getA() + ints.getB() + ints.getC();
    }

    private static class Ints {
        private final int a;
        private final int b;
        private final int c;

        private Ints(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }
    }
}