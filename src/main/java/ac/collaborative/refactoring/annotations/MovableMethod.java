package ac.collaborative.refactoring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MovableMethod annotation indicates that we may want to apply the Move Method refactoring action.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface MovableMethod {
	String Description();
}