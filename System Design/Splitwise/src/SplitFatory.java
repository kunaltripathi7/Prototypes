public class SplitFatory {
    public static Split ofType(String type) throws Exception {
        switch (type) {
            case "EQUAL": return new EqualSplit();

        }

        throw new Exception("no matcheing");
    }
}
