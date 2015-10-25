    public enum Roles {

        READ, WRITE, EXEC;

        private static int checkRoles( Roles role ) {
            switch ( role ) {

                case READ:
                    return 1;

                case WRITE:
                    return 2;

                case EXEC:
                    return 4;

                default:
                    throw new RuntimeException("!!!");

            }
        }

    }

