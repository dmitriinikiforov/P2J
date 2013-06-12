// Generated from C:\Users\Dima\Documents\NetBeansProjects\P2J\src\p2j\Prolog.g4 by ANTLR 4.0
package p2j;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrologLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VARIABLE=1, FUNCTOR=2, NUMBER=3, EXPONENT=4, DOT=5, AND=6, OR=7, ASSIGN=8, 
		LOWERCASE=9, UPPERCASE=10, DIGIT=11, ANONIMUS=12, STRING=13, LEFT_BRACE=14, 
		RIGHT_BRACE=15, LIST_BEGIN=16, LIST_LINE=17, LIST_END=18, NEWLINE=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"VARIABLE", "FUNCTOR", "NUMBER", "EXPONENT", "'.'", "','", "';'", "':-'", 
		"LOWERCASE", "UPPERCASE", "DIGIT", "'_'", "STRING", "'('", "')'", "'['", 
		"'|'", "']'", "NEWLINE"
	};
	public static final String[] ruleNames = {
		"VARIABLE", "FUNCTOR", "NUMBER", "EXPONENT", "DOT", "AND", "OR", "ASSIGN", 
		"LOWERCASE", "UPPERCASE", "DIGIT", "ANONIMUS", "STRING", "LEFT_BRACE", 
		"RIGHT_BRACE", "LIST_BEGIN", "LIST_LINE", "LIST_END", "NEWLINE"
	};


	public PrologLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Prolog.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\2\4\25\u00a1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\3\2\3\2\5\2,\n\2\3\2\3\2"+
		"\3\2\3\2\7\2\62\n\2\f\2\16\2\65\13\2\3\3\3\3\3\3\3\3\3\3\7\3<\n\3\f\3"+
		"\16\3?\13\3\3\4\6\4B\n\4\r\4\16\4C\3\4\3\4\6\4H\n\4\r\4\16\4I\5\4L\n\4"+
		"\3\4\5\4O\n\4\3\4\3\4\6\4S\n\4\r\4\16\4T\3\4\5\4X\n\4\5\4Z\n\4\3\5\3\5"+
		"\3\5\5\5_\n\5\3\5\3\5\3\5\5\5d\n\5\5\5f\n\5\3\5\5\5i\n\5\3\5\6\5l\n\5"+
		"\r\5\16\5m\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\7\16\u0083\n\16\f\16\16\16\u0086\13\16\3\16\3\16"+
		"\3\16\7\16\u008b\n\16\f\16\16\16\u008e\13\16\3\16\5\16\u0091\n\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\5\24\u009e\n\24\3\24"+
		"\3\24\2\25\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1"+
		"\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25"+
		"\1\3\2\b\4--//\3c|\3C\\\3\62;\3$$\3))\u00b9\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\3+\3\2\2\2\5\66\3\2\2\2\7Y\3\2\2\2\te\3\2\2\2\13o\3\2\2\2\rq\3\2\2"+
		"\2\17s\3\2\2\2\21u\3\2\2\2\23x\3\2\2\2\25z\3\2\2\2\27|\3\2\2\2\31~\3\2"+
		"\2\2\33\u0090\3\2\2\2\35\u0092\3\2\2\2\37\u0094\3\2\2\2!\u0096\3\2\2\2"+
		"#\u0098\3\2\2\2%\u009a\3\2\2\2\'\u009d\3\2\2\2),\5\25\13\2*,\7a\2\2+)"+
		"\3\2\2\2+*\3\2\2\2,\63\3\2\2\2-\62\5\23\n\2.\62\5\25\13\2/\62\5\27\f\2"+
		"\60\62\7a\2\2\61-\3\2\2\2\61.\3\2\2\2\61/\3\2\2\2\61\60\3\2\2\2\62\65"+
		"\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\4\3\2\2\2\65\63\3\2\2\2\66=\5"+
		"\23\n\2\67<\5\23\n\28<\5\25\13\29<\5\27\f\2:<\7a\2\2;\67\3\2\2\2;8\3\2"+
		"\2\2;9\3\2\2\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\6\3\2\2\2?=\3"+
		"\2\2\2@B\5\27\f\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2DK\3\2\2\2EG"+
		"\5\13\6\2FH\5\27\f\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2"+
		"KE\3\2\2\2KL\3\2\2\2LN\3\2\2\2MO\5\t\5\2NM\3\2\2\2NO\3\2\2\2OZ\3\2\2\2"+
		"PR\5\13\6\2QS\5\27\f\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2"+
		"\2VX\5\t\5\2WV\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YA\3\2\2\2YP\3\2\2\2Z\b\3\2"+
		"\2\2[^\7g\2\2\\]\7z\2\2]_\7r\2\2^\\\3\2\2\2^_\3\2\2\2_f\3\2\2\2`c\7G\2"+
		"\2ab\7z\2\2bd\7r\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2e[\3\2\2\2e`\3\2\2\2"+
		"fh\3\2\2\2gi\t\2\2\2hg\3\2\2\2hi\3\2\2\2ik\3\2\2\2jl\5\27\f\2kj\3\2\2"+
		"\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\n\3\2\2\2op\7\60\2\2p\f\3\2\2\2qr\7"+
		".\2\2r\16\3\2\2\2st\7=\2\2t\20\3\2\2\2uv\7<\2\2vw\7/\2\2w\22\3\2\2\2x"+
		"y\t\3\2\2y\24\3\2\2\2z{\t\4\2\2{\26\3\2\2\2|}\t\5\2\2}\30\3\2\2\2~\177"+
		"\7a\2\2\177\32\3\2\2\2\u0080\u0084\7$\2\2\u0081\u0083\n\6\2\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0091\7$\2\2\u0088\u008c\7)\2"+
		"\2\u0089\u008b\n\7\2\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f"+
		"\u0091\7)\2\2\u0090\u0080\3\2\2\2\u0090\u0088\3\2\2\2\u0091\34\3\2\2\2"+
		"\u0092\u0093\7*\2\2\u0093\36\3\2\2\2\u0094\u0095\7+\2\2\u0095 \3\2\2\2"+
		"\u0096\u0097\7]\2\2\u0097\"\3\2\2\2\u0098\u0099\7~\2\2\u0099$\3\2\2\2"+
		"\u009a\u009b\7_\2\2\u009b&\3\2\2\2\u009c\u009e\7\17\2\2\u009d\u009c\3"+
		"\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\7\f\2\2\u00a0"+
		"(\3\2\2\2\30\2+\61\63;=CIKNTWY^cehm\u0084\u008c\u0090\u009d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}