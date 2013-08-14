package com.jauntsy.driller.demo;

import com.jauntsy.driller.api.*;
import com.jauntsy.driller.impl.cascading.CascadingDrillerFactory;
import com.jauntsy.driller.io.CsvWriter;
import com.jauntsy.driller.op.*;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;

import static com.jauntsy.driller.api.Schema.Types.*;
import static com.jauntsy.driller.api.MapReduce.*;

import java.io.Serializable;
import java.util.Iterator;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:50 PM
 */
public class WordCount extends Configured implements Tool, Serializable {

    private Driller newDriller() {
        return new CascadingDrillerFactory().createPipeline(getConf(), WordCount.class);
    }

    public void untyped() {
        Driller mr = newDriller();

        Table lines = mr.loadText("/tmp/quotes.txt");
        Table wordsCounts = lines.mapTo(
                Table.of(strings("word"), longs("count")),
                new Mapper() {
                    public void map(Tuple tuple, Emitter e) {
                        for (String word : tuple.getString(1).split("\\s"))
                            e.emit(Tuple.of2(word, 1L));
                    }
                });
        wordsCounts.groupBy(1).combine(new Combiner() {
            @Override
            public Tuple combine(Tuple group, Tuples tuples) {
                long total = 0L;
                while (tuples.hasNext())
                    total += tuples.next().getLong(1);
                return Tuple.of1(total);
            }
        }).storeAsText("/tmp/wc/untyped", new Mapper() {
            @Override
            public void map(Tuple tuple, Emitter e) {
                e.emit(Tuple.of1(tuple.get(1) + "," + tuple.get(2)));
            }
        });
    }

    public void typed() {
        Driller mr = newDriller();
        Table1<String> lines = mr.loadText("/tmp/quotes.txt");
        Table2<String,Long> wordsCounts = lines.mapTo(
                Table.of(strings("word"), longs("count")),
                new Mapper<Tuple1<String>, Tuple2<String, Long>>() {
                    public void map(Tuple1<String> tuple, Emitter e) {
                        for (String word : tuple.get1st().split("\\s"))
                            e.emit(Tuple.of2(word, 1L));
                    }
                });
        Grouped2<String, Long, Tuple1<String>, Tuple1<Long>> grouped = wordsCounts.groupBy1();
        grouped.combine(new Combiner<Tuple1<String>, Tuple1<Long>>() {
            @Override
            public Tuple1<Long> combine(Tuple1<String> group, Tuples<Tuple1<Long>> tuples) {
                long total = 0L;
                while (tuples.hasNext())
                    total += tuples.next().get1st();
                return Tuple.of1(total);
            }
        }).storeAsText("/tmp/wc/typed", new Mapper<Tuple2<String, Long>, Tuple1<String>>() {
            @Override
            public void map(Tuple2<String, Long> tuple, Emitter e) {
                e.emit(Tuple.of1(tuple.get1st() + "," + tuple.get2nd()));
            }
        });
    }

    public void typedB() {
        Driller mr = newDriller();
        Table2<String,Long> wordCounts = mr.loadText("/tmp/quotes.txt").mapTo(Table.of(strings("word"), longs("count")), new Mapper<Tuple1<String>, Tuple2<String, Long>>() {
            public void map(Tuple1<String> tuple, Emitter e) {
                for (String word : tuple.get1st().split("\\s"))
                    e.emit(Tuple.of2(word, 1L));
            }
        });
        wordCounts.groupBy1().combine(new Combiner<Tuple1<String>, Tuple1<Long>>() {
            @Override
            public Tuple1<Long> combine(Tuple1<String> group, Tuples<Tuple1<Long>> tuples) {
                long total = 0L;
                while (tuples.hasNext())
                    total += tuples.next().get1st();
                return Tuple.of1(total);
            }
        }).storeAsText("/tmp/wc/typed", new Mapper<Tuple2<String, Long>, Tuple1<String>>() {
            @Override
            public void map(Tuple2<String, Long> tuple, Emitter e) {
                e.emit(Tuple.of1(tuple.get1st() + "," + tuple.get2nd()));
            }
        });
    }

    public void typedC() {
        Driller mr = newDriller();
        mr.loadText("/tmp/quotes.txt").mapTo(Table.of(strings("word"), longs("count")), new Mapper<Tuple1<String>, Tuple2<String, Long>>() {
            public void map(Tuple1<String> tuple, Emitter e) {
                for (String word : tuple.get1st().split("\\s"))
                    e.emit(Tuple.of2(word, 1L));
            }
        }).groupBy("word").combine(new Adder<Tuple1<String>, Tuple1<Long>>(Tuple.of(0L)) {
            @Override
            public Tuple1<Long> add(Tuple1<Long> a, Tuple1<Long> b) {
                return Tuple.of(a.get1st() + b.get1st());
            }
        }).storeAsText("/tmp/wc/typed", new Mapper<Tuple2<String, Long>, Tuple1<String>>() {
            @Override
            public void map(Tuple2<String, Long> tuple, Emitter e) {
                e.emit(Tuple.of1(tuple.get1st() + "," + tuple.get2nd()));
            }
        });
    }

    /*
    public void typedC() {
        Driller mr = newDriller();
        mr.loadText("/tmp/quotes.txt")
          .mapTo(Table.of(strings("word"), longs("count")), (tuple, e) -> {
              for (String word : tuple.get1st().split("\\s"))
                  e.emit(T(word, 1L));
          })
          .groupBy("word").add((a, b) -> {
              return T(a.get1st() + b.get1st());
          }).storeAsText("/tmp/wc/typed", (tuple, e) -> {
              e.emit(T(tuple.get1st() + "," + tuple.get2nd()));
          });
    }
    */


//    public void typedAlt() {
//        Driller mr = newDriller();
//        Table1<String> lines = mr.loadText("/tmp/quotes.txt");
//        Table2<String,Long> wordsCounts = lines.mapTo(
//                Table.of(strings(), longs()),
//                new Mapper<Tuple1<String>, Tuple2<String, Long>>() {
//                    public void map(Tuple1<String> tuple, Emitter e) {
//                        for (String word : tuple.get1st().split("\\s"))
//                            e.emit(Tuple.of2(word, 1L));
//                    }
//                }).as("word", "count");
//        wordsCounts.groupBy("word").combine(new Combiner<Tuple1<String>,Tuple1<Long>>() {
//            @Override
//            public Tuple2<String, Long> combine(Tuple2<String, Long> a, Tuple2<String, Long> b) {
//                a.set2nd(a.get2nd() + b.get2nd());
//                return a;
//            }
//        }).storeAsText("/tmp/wc/typed", new Mapper<Tuple2<String, Long>, Tuple1<String>>() {
//            @Override
//            public void map(Tuple2<String, Long> tuple, Emitter e) {
//                e.emit(Tuple.of1(tuple.get1st() + "," + tuple.get2nd()));
//            }
//        });
//    }


    public void typed2() {
        Driller mr = newDriller();

        Table1<String> lines = mr.loadText("/tmp/quotes.txt");
        Table1<String> words = lines.mapTo(
                Schema.Of(strings("word")),
                new Mapper<Tuple1<String>, Tuple1<String>>() {
                    public void map(Tuple1<String> tuple, Emitter e) {
                        for (String word : tuple.get1st().split("\\s"))
                            e.emit(Tuple.of1(word));
                    }
                });
        Table2<String,Long> counts = Aggregate.count(words);
        counts.storeAsText("/tmp/wc/typed2", new CsvWriter());
    }

    public void untyped2() {
        Driller mr = newDriller();

        Table lines = mr.loadText("/tmp/quotes.txt");
        Table words = lines.mapTo(
                Schema.Of(strings("word")),
                new Mapper() {
                    public void map(Tuple tuple, Emitter e) {
                        for (String word : tuple.getString("text").split("\\s"))
                            e.emit(Tuple.of1(word));
                    }
                });
        Table counts = Aggregate.count(words);
        counts.storeAsText("/tmp/wc/untyped2", new CsvWriter());
    }

    public void typed3() {
        Driller mr = newDriller();

        Table1<String> lines = mr.loadText("/tmp/quotes.txt");
        Table2<String,Long> wordsCounts = lines.mapTo(
                Schema.Of(strings("word"), longs("count")),
                new Mapper<Tuple1<String>, Tuple2<String, Long>>() {
                    public void map(Tuple1<String> tuple, Emitter e) {
                        for (String word : tuple.get1st().split("\\s"))
                            e.emit(Tuple.of2(word, 1L));
                    }
                });
        Grouped2<String, Long, Tuple1<String>, Tuple1<Long>> grouped = wordsCounts.groupBy1();
        wordsCounts = grouped.reduce(
                Schema.Of(strings("word"),longs("count")),
                new Reducer<Tuple1<String>, Tuple1<Long>, Tuple2<String, Long>>() {
                    @Override
                    public void reduce(Tuple1<String> key, Tuples<Tuple1<Long>> values, Emitter<Tuple2<String, Long>> e) {
                        long count = 0L;
                        while (values.hasNext()) {
                            Tuple1<Long> next = values.next();
                            count += next.get1st();
                        }
                        e.emit(Tuple.of2(key.get1st(), count));
                    }
                }
        );
        wordsCounts.storeAsText("/tmp/wc/typed3", new CsvWriter());
    }

    public void untyped3() {
        Driller mr = newDriller();

        Table lines = mr.loadText("/tmp/quotes.txt");
        Table wordsCounts = lines.mapTo(
                Schema.Of(strings("word"), longs("count")),
                new Mapper() {
                    @Override
                    public void map(Tuple lines, Emitter e) {
                        for (String word : lines.getString(1).split("\\s")) {
                            e.emit(Tuple.of2(word, 1L));
                        }
                    }
                });
        Grouped grouped = wordsCounts.groupBy(1);
        wordsCounts = grouped.reduce(
                Schema.Of(strings("word"), longs("count")),
                new Reducer() {
                    @Override
                    public void reduce(Tuple key, Tuples values, Emitter e) {
                        long count = 0L;
                        while (values.hasNext())
                            count += values.next().getLong(1);
                        e.emit(Tuple.of2(key.getString(1), count));
                    }
                });
        wordsCounts.storeAsText("/tmp/wc/untyped3", new CsvWriter());
    }

    public void untyped3B() {
        Driller mr = newDriller();
        Table lines = mr.loadText("/tmp/quotes.txt");
        Table wordsCounts = forEach(lines).generate(Table.of(strings("word"), longs("count")), new Mapper() {
            @Override
            public void map(Tuple tuple, Emitter e) {
                for (String word : tuple.getString(1).split("\\s")) {
                    e.emit(Tuple.of2(word, 1L));
                }
            }
        });
        _Group<Tuple, Tuple, Table> group = group(wordsCounts);
        _Group<Tuple, Tuple, Table>._ByCols by = group.by("word");
        Grouped combine = by.combine(new Combiner() {
            public Tuple combine(Tuple word, Tuples counts) {
                long count = 0L;
                while (counts.hasNext())
                    count += counts.next().getLong(1);
                return Tuple.of1(count);
            }
        });
        wordsCounts = combine.ungroup();
        wordsCounts.storeAsText("/tmp/wc/untyped3b", new CsvWriter());
    }

//    public void untyped3C() {
//        Driller mr = newDriller();
//
//        Table1<String> lines = mr.loadText("/tmp/quotes.txt");
//        Table2<String,Long> wordsCounts = forEach(lines).generate(
//                Table.of(strings("word"), longs("count")),
//                new Mapper<Tuple1<String>, Tuple2<String, Long>>() {
//                    @Override
//                    public void map(Tuple1<String> lines, Emitter e) {
//                        for (String word : lines.getString(1).split("\\s")) {
//                            e.emit(Tuple.of2(word, 1L));
//                        }
//                    }
//                });
//        wordsCounts = group(wordsCounts).by("word").combine(new Combiner() {
//            public Tuple combine(Tuple word, Tuples counts) {
//                long count = 0L;
//                while (counts.hasNext())
//                    count += counts.next().getLong(1);
//                return Tuple.of1(count);
//            }
//        }).ungroup();
//        wordsCounts.storeAsText("/tmp/wc/untyped3", new CsvWriter());
//    }

    /*
    public void typedAndSorted() {
        Schema1<String> word = Table.of(strings("word"));
        Driller mr = newDriller();
        Table1<String> lines = mr.loadText("/tmp/quotes.txt");
        Table1<String> words = lines.mapTo(
                Table.of(strings("word")),
                new Mapper<Tuple1<String>, Tuple1<String>>() {
                    public void map(Tuple1<String> tuple, Emitter e) {
                        for (String word : tuple.get1st().split("\\s"))
                            e.emit(Tuple.of1(word));
                    }
                });
        Aggregate.count(words).sortBy("count", -1, "word", 1).storeAsText("/tmp/typedAndSorted", new CsvWriter());
    }

    public void untypedAndSorted() {
        Driller mr = newDriller();
        Table lines = mr.loadText("/tmp/quotes.txt");
        Table words = lines.mapTo(
                Table.of(strings("word")),
                new Mapper<Tuple, Tuple>() {
                    public void map(Tuple tuple, Emitter e) {
                        for (String word : tuple.getString(1).split("\\s"))
                            e.emit(Tuple.of1(word));
                    }
                });
        Aggregate.count(words).sortBy("count", -1, "word", 1).storeAsText("/tmp/typedAndSorted", new CsvWriter());
    }                            */

//    public void moviesWithRatingCountAndAverageRating() {
//        Driller mr = null;
//        Table4<Long,String,Long,Long> idNameCountTotal = mr.loadText("/tmp/movies").mapTo(
//                Table.of(longs("movieId"), strings("movieName"), longs("rcount"), longs("rtotal")),
//                new Mapper<Tuple1<String>, Tuple4<Long,String,Long,Long>>() {
//                    @Override
//                    public void map(Tuple1<String> tuple, Emitter e) {
//                        String[] cols = tuple.get1st().split(",");
//                        e.emit(Tuple.of4(new Long(cols[0]), cols[1], 0L, 0L));
//                    }
//                }
//        ).addTextSource("/tmp/ratings", new Mapper<Tuple1<String>, Tuple5<Long, Long, String, Long, Long>>() {
//            @Override
//            public void map(Tuple1<String> tuple, Emitter e) {
//                String[] cols = tuple.get1st().split(",");
//                e.emit(Tuple.of5(new Long(cols[0]), 1L, (String) null, 1L, new Long(cols[1])));
//            }
//        });
//        idNameCountTotal.groupBy1().combine(new Combiner<Tuple1<Long>,Tuple4<Long, String, Long, Long>>() {
//            @Override
//            public Tuple4<Long, String, Long, Long> combine(Tuple1<Long> group, Tuples<Tuple4<Long, String, Long, Long>> values) {
//                long id = group.get1st(), count = 0L, total = 0L;
//                String movieName = null;
//                while (values.hasNext()) {
//                    Tuple4<Long,String,Long,Long> next = values.next();
//                    if (next.get2nd() != null) movieName = next.get2nd();
//                    count += next.get3rd();
//                    total += next.get4th();
//                }
//                return Tuple.of4(id, movieName, count, total);
//            }
//        }).rereduce(
//                Schema.Of(longs("movieId"), strings("movieName"), longs("numberOfRatings"), longs("averageRating")),
//                new ReReducer<Tuple4<Long,String,Long,Long>, Tuple4<Long, String, Long, Long>>() {
//                    @Override
//                    public Tuple4<Long,String,Long,Long> rereduce(Tuple4<Long, String, Long, Long> tuple) {
//                        return Tuple.of4(tuple.get1st(), tuple.get2nd(), tuple.get3rd(), tuple.get4th() / tuple.get3rd());
//                    }
//                }
//        );
//    }

//    public void moviesWithRatingCountAndAverageRatingUntyped() {
//        Driller mr = null;
//        Table idNameCountTotal = mr.loadText("/tmp/movies").mapTo(
//                Table.of(longs("movieId"), strings("movieName"), longs("rcount"), longs("rtotal")),
//                new Mapper() {
//                    @Override
//                    public void map(Tuple tuple, Emitter e) {
//                        String[] cols = tuple.getString(1).split(",");
//                        e.emit(Tuple.of4(new Long(cols[0]), cols[1], 0L, 0L));
//                    }
//                }
//        ).addTextSource("/tmp/ratings", new Mapper<Tuple1<String>, Tuple5<Long, Long, String, Long, Long>>() {
//            @Override
//            public void map(Tuple1<String> tuple, Emitter e) {
//                String[] cols = tuple.get1st().split(",");
//                e.emit(Tuple.of5(new Long(cols[0]), 1L, (String) null, 1L, new Long(cols[1])));
//            }
//        });
//        idNameCountTotal.groupBy(1).combine(new Combiner() {
//            @Override
//            public Tuple combine(Tuple group, Tuples values) {
//                long id = group.getLong(1), count = 0L, total = 0L;
//                String movieName = null;
//                while (values.hasNext()) {
//                    Tuple next = values.next();
//                    if (next.getString(2) != null) movieName = next.getString(2);
//                    count += next.getLong(3);
//                    total += next.getLong(4);
//                }
//                return Tuple.of4(id, movieName, count, total);
//            }
//        }).rereduce(
//                Schema.Of(longs("movieId"), strings("movieName"), longs("numberOfRatings"), longs("averageRating")),
//                new ReReducer<Tuple4<Long,String,Long,Long>, Tuple4<Long, String, Long, Long>>() {
//                    @Override
//                    public Tuple4<Long,String,Long,Long> rereduce(Tuple4<Long, String, Long, Long> tuple) {
//                        return Tuple.of4(tuple.get1st(), tuple.get2nd(), tuple.get3rd(), tuple.get4th() / tuple.get3rd());
//                    }
//                }
//        );
//    }

//    public void moviesWithRatingCountAndAverageRatingUntyped() {
//        Driller mr = null;
//        Table idNameCountTotal = mr.loadText("/tmp/movies").mapTo(
//                Table.of(longs("movieId"), strings("movieName"), longs("rcount"), longs("rtotal")),
//                new Mapper() {
//                    @Override
//                    public void map(Tuple tuple, Emitter e) {
//                        String[] cols = tuple.getString(1).split(",");
//                        e.emit(Tuple.of4(new Long(cols[0]), cols[1], 0L, 0L));
//                    }
//                }
//        ).addTextSource("/tmp/ratings", new Mapper<Tuple1<String>, Tuple5<Long, Long, String, Long, Long>>() {
//            @Override
//            public void map(Tuple1<String> tuple, Emitter e) {
//                String[] cols = tuple.get1st().split(",");
//                e.emit(Tuple.of5(new Long(cols[0]), 1L, (String) null, 1L, new Long(cols[1])));
//            }
//        });
//        idNameCountTotal.groupBy("movieId").combine(ColumnCombiner.build(new FirstString(), new SumLongs(), new SumLongs())).rereduce(
//                Schema.Of(longs("movieId"), strings("movieName"), longs("numberOfRatings"), longs("averageRating")),
//                new ReReducer<Tuple4<Long, String, Long, Long>, Tuple4<Long, String, Long, Long>>() {
//                    @Override
//                    public Tuple4<Long, String, Long, Long> rereduce(Tuple4<Long, String, Long, Long> tuple) {
//                        return Tuple.of4(tuple.get1st(), tuple.get2nd(), tuple.get3rd(), tuple.get4th() / tuple.get3rd());
//                    }
//                }
//        );
//    }

    @Override
    public int run(String[] strings) throws Exception {
//        typed();
//        untyped();
//        typed2();
//        untyped2();
        typed3();
        untyped3();
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /*
    lines.map(Schema.of(strings("word"),longs("count")), ...)
    liens.map(Schema.of(strings("email"), strings("name"), ints("age"), rels("addresses", Schema.of(
     */

}
