package ac.collaborative.refactoring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RefactorableMethod annotation indicates that we may want to apply refactoring action for the annotated method.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface RefactorableMethod {
	String Description();
}
