package otus.spring.homework6orm.dao;

sealed public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

     final public static class EntityNotFound extends DaoException {
        public EntityNotFound(String message) {
            super(message);
        }
    }

    final public static class FailedToCreateEntityRelation extends DaoException {
        public FailedToCreateEntityRelation(String message) {
            super(message);
        }

        public FailedToCreateEntityRelation(String message, Throwable cause) {
            super(message, cause);
        }
    }
}




