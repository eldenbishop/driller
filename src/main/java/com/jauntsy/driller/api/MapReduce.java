package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 7/5/12
 * Time: 4:14 PM
 */
public class MapReduce {

    public static <
            CommonTupleType extends Tuple,
            TupleType extends Tuple<CommonTupleType,TupleType>,
            TableType extends Table<CommonTupleType,TupleType,TableType>
            > _ForEach<CommonTupleType, TupleType, TableType> forEach(TableType table) {
        return new _ForEach<CommonTupleType,TupleType,TableType>(table);
    }

    public static <
            CommonTupleType extends Tuple,
            TupleType extends Tuple<CommonTupleType,TupleType>,
            TableType extends Table
            > _Group<CommonTupleType, TupleType, TableType> group(TableType table) {
        return new _Group<CommonTupleType,TupleType,TableType>(table);
    }

    public static <
            A,
            CommonTupleType extends Tuple,
            TupleType extends Tuple<CommonTupleType,TupleType>,
            TableType extends Table1<A>
            > _Group1<A,CommonTupleType, TupleType, TableType> group(TableType table) {
        return new _Group1<A,CommonTupleType,TupleType,TableType>(table);
    }

    public static <
            A,B,
            CommonTupleType extends Tuple,
            TupleType extends Tuple<CommonTupleType,TupleType>,
            TableType extends Table2<A,B>
            > _Group2<A,B,CommonTupleType, TupleType, TableType> group(TableType table) {
        return new _Group2<A,B,CommonTupleType,TupleType,TableType>(table);
    }

    public static class _ForEach<
            CommonTupleType extends Tuple,
            TupleType extends Tuple,
            TableType extends Table
    > {

        private TableType table;

        public _ForEach(TableType table) {
            this.table = table;
        }

        public <
                DstTupleType extends Tuple,
                DstTableType extends Table
        > DstTableType generate(Schema<?,DstTupleType,DstTableType,?> schema, Mapper<? super TupleType,DstTupleType> mapper) {
            return (DstTableType) table.mapTo(schema, mapper);
        }

        public <
                DstTupleType extends Tuple,
                DstTableType extends Table
        > DstTableType generate(Schema<CommonTupleType,DstTupleType,DstTableType,?> schema) {
            return (DstTableType) table.mapTo(schema);
        }
    }

    public static class _Group<
            CommonTupleType extends Tuple,
            TupleType extends Tuple,
            TableType extends Table
    > {

        private TableType table;

        public _Group(TableType table) {
            this.table = table;
        }

        public _ByDepth by(int depth) { return new _ByDepth(depth); }

        public _ByCols by(String... columnNames) { return new _ByCols(columnNames); }

        public class _ByCols {
            String[] columnNames;
            public _ByCols(String... columnNames) {
                this.columnNames = columnNames;
            }

            public Grouped combine(Combiner combiner) {
                return table.groupBy(columnNames).combine(combiner);
            }
        }

        public class _ByDepth {
            int depth;
            public _ByDepth(int depth) {
                this.depth = depth;
            }

            public Grouped combine(Combiner combiner) {
                return table.groupBy(depth).combine(combiner);
            }
        }
    }

    public static class _Group1<
            A,
            CommonTupleType extends Tuple,
            TupleType extends Tuple,
            TableType extends Table
    > {

        private TableType table;

        public _Group1(TableType table) {
            this.table = table;
        }

    }

    public static class _Group2<
            A,B,
            CommonTupleType extends Tuple,
            TupleType extends Tuple,
            TableType extends Table
    > {

        private TableType table;

        public _Group2(TableType table) {
            this.table = table;
        }

    }

}
