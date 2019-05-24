package pageobj.pages.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import pageobj.elements.IWebElements;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<IWebElements> clazz;
    private final WebDriver webDriver;

    public LocatingCustomElementListHandler(ElementLocator locator,
                                            Class<IWebElements> clazz, WebDriver webDriver) {
        this.locator = locator;
        this.clazz = clazz;
        this.webDriver = webDriver;
    }

    public Object invoke(Object object, Method method,
                         Object[] objects) throws Throwable {
        // Находит список WebElement и обрабатывает каждый его элемент,
        // возвращает новый список с элементами кастомного класса
        List<WebElement> elements = locator.findElements();
        List<IWebElements> customs = new ArrayList<IWebElements>();

        for (WebElement element : elements) {
            customs.add(WrapperFactory.createInstance(clazz, element,webDriver));
        }
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

}