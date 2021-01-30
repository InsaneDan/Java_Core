package lesson7;

class TestSet {

    @Test(priority = 5)
    public void Test1() {
        System.out.println("Тест 1");
    }

    @Test
    public void Test2() {
        System.out.println("Тест 2 default priority");
    }

    @Test(priority = 1)
    public void Test3() {
        System.out.println("Тест 3");
    }

    @Test(priority = 10)
    public void Test4() {
        System.out.println("Тест 4");
    }

    @Test(priority = 3)
    public void Test5() {
        System.out.println("Тест 5");
    }

    @BeforeSuite
    public void BeforeTest() {
        System.out.println("Тест BeforeSuite");
    }

    @AfterSuite
    public void AfterTest() {
        System.out.println("Тест AfterSuite");
    }
}
