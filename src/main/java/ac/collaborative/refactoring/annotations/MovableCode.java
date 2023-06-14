package ac.collaborative.refactoring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MovableCode annotation indicates that we may want to apply the Extract Method + Move Method refactoring actions.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface MovableCode {
	String Description();
}
