package ac.code.verifier.engine;

/**
 * The enum CodeFragmentResultEnum represents the possible  problems in the fragment.
 *
 */
public enum CodeFragmentResultEnum {
	OK,
	BROKEN_STATEMENT,
	ORPHAN_CASE,
	ORPHAN_CONTINUE,
	ORPHAN_BREAK,
	ORPHAN_BLOCK
}
