public class TupleTest {
    public static void main(String[] args) {
        var triPle = Tuple.<Object>of(3,"Casa", 3.44345f, new Object[]{ "Pedrito",21, 94.f});
        StdOut.printf("tuple = %s\n",triPle);
        StdOut.printf ("sublist = %s\n",triPle.subList(1,2));
        StdOut.printf("size = %d\n",triPle.size());
        float got0 = triPle.getCasted(2);
        StdOut.printf("pos0 = %f\n",got0);
        triPle.<Object[]>getCasted(3)[0] = "Pedro";
        StdOut.printf("tuple = %s\n",triPle);

        var duple = Tuple.<Integer>of(2,4);

        StdOut.println(duple.get(0) + duple.get(1));

        duple.forEach(StdOut::println);
        Tuple<Object> forthtuple = Tuple.of("Hola",2,3,4);
        var x = triPle.join(forthtuple);
        StdOut.printf("Joint = %s", x);
    }
}
