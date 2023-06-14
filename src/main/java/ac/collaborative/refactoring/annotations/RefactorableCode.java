package ac.collaborative.refactoring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RefactorableCode annotation indicates that we may want to apply refactoring action for the marked code fragment.
 *
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface RefactorableCode {
	String Description();
}

