package io.presentation.jpa.entitymapping.entity;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
public enum LockerState {
    IN_USED {
        @Override
        public boolean isBorrowable() {
            return false;
        }
    },

    NOT_USED {
        @Override
        public boolean isBorrowable() {
            return true;
        }
    };

    public abstract boolean isBorrowable();
}
