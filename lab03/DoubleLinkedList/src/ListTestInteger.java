public class ListTestInteger extends ListTest<Integer>{
    private static int i = 0;
	@Override
	public Integer getParameterInstance() {
		return i++;}
}
