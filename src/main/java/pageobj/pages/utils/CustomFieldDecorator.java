package pageobj.pages.utils;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import pageobj.elements.IWebElements;

import java.lang.reflect.*;
import java.util.List;

public class CustomFieldDecorator extends DefaultFieldDecorator {
    private WebDriver webDriver;
    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
        this.webDriver = (WebDriver) searchContext;
    }

    /**
     * Метод вызывается фабрикой для каждого поля в классе
     */

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<IWebElements> decoratableClass = decoratableClass(field);
        // если класс поля декорируемый
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    /**
     * Возвращает декорируемый класс поля,
     * либо null если класс не подходит для декоратора
     */
    @SuppressWarnings("unchecked")
    private Class<IWebElements> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            // для списка обязательно должна быть задана аннотация
            if (field.getAnnotation(FindBy.class) == null &&
                    field.getAnnotation(FindBys.class) == null) {
                return null;
            }

            // Список должен быть параметризирован
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // получаем класс для элементов списка
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (IWebElements.class.isAssignableFrom(clazz)) {
            return (Class<IWebElements>) clazz;
        }
        else {
            return null;
        }
    }

    /**
     * Создание элемента.
     * Находит WebElement и передает его в кастомный класс
     */
    protected IWebElements createElement(ClassLoader loader,
                                     ElementLocator locator,
                                     Class<IWebElements> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy, webDriver);
    }

    /**
     * Создание списка
     */
    @SuppressWarnings("unchecked")
    protected List<IWebElements> createList(ClassLoader loader,
                                        ElementLocator locator,
                                        Class<IWebElements> clazz) {

        InvocationHandler handler =
                new LocatingCustomElementListHandler(locator, clazz, webDriver);
        List<IWebElements> elements =
                (List<IWebElements>) Proxy.newProxyInstance(
                        loader, new Class[] {List.class}, handler);
        return elements;
    }



}
