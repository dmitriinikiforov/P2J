// Generated from C:\Users\Dima\Documents\NetBeansProjects\P2J\src\p2j\Prolog.g4 by ANTLR 4.0
package p2j;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrologParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VARIABLE=1, FUNCTOR=2, NUMBER=3, EXPONENT=4, DOT=5, AND=6, OR=7, ASSIGN=8, 
		LOWERCASE=9, UPPERCASE=10, DIGIT=11, ANONIMUS=12, STRING=13, LEFT_BRACE=14, 
		RIGHT_BRACE=15, LIST_BEGIN=16, LIST_LINE=17, LIST_END=18, NEWLINE=19;
	public static final String[] tokenNames = {
		"<INVALID>", "VARIABLE", "FUNCTOR", "NUMBER", "EXPONENT", "'.'", "','", 
		"';'", "':-'", "LOWERCASE", "UPPERCASE", "DIGIT", "'_'", "STRING", "'('", 
		"')'", "'['", "'|'", "']'", "NEWLINE"
	};
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_structure = 2, RULE_argument = 3, 
		RULE_list = 4, RULE_tail = 5;
	public static final String[] ruleNames = {
		"program", "statement", "structure", "argument", "list", "tail"
	};

	@Override
	public String getGrammarFileName() { return "Prolog.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrologParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FUNCTOR) {
				{
				{
				setState(12); statement();
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public List<StructureContext> structure() {
			return getRuleContexts(StructureContext.class);
		}
		public TerminalNode NEWLINE() { return getToken(PrologParser.NEWLINE, 0); }
		public List<TerminalNode> AND() { return getTokens(PrologParser.AND); }
		public TerminalNode DOT() { return getToken(PrologParser.DOT, 0); }
		public TerminalNode AND(int i) {
			return getToken(PrologParser.AND, i);
		}
		public TerminalNode ASSIGN() { return getToken(PrologParser.ASSIGN, 0); }
		public StructureContext structure(int i) {
			return getRuleContext(StructureContext.class,i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18); structure();
			setState(28);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(19); match(ASSIGN);
				setState(20); structure();
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(21); match(AND);
					setState(22); structure();
					}
					}
					setState(27);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(30); match(DOT);
			setState(32);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(31); match(NEWLINE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructureContext extends ParserRuleContext {
		public TerminalNode RIGHT_BRACE() { return getToken(PrologParser.RIGHT_BRACE, 0); }
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public TerminalNode FUNCTOR() { return getToken(PrologParser.FUNCTOR, 0); }
		public List<TerminalNode> AND() { return getTokens(PrologParser.AND); }
		public TerminalNode LEFT_BRACE() { return getToken(PrologParser.LEFT_BRACE, 0); }
		public TerminalNode AND(int i) {
			return getToken(PrologParser.AND, i);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public StructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitStructure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitStructure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructureContext structure() throws RecognitionException {
		StructureContext _localctx = new StructureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_structure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); match(FUNCTOR);
			setState(46);
			_la = _input.LA(1);
			if (_la==LEFT_BRACE) {
				{
				setState(35); match(LEFT_BRACE);
				setState(36); argument();
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(37); match(AND);
					setState(38); argument();
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(44); match(RIGHT_BRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public StructureContext structure() {
			return getRuleContext(StructureContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(PrologParser.VARIABLE, 0); }
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public TerminalNode STRING() { return getToken(PrologParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(PrologParser.NUMBER, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_argument);
		int _la;
		try {
			setState(51);
			switch (_input.LA(1)) {
			case VARIABLE:
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARIABLE) | (1L << NUMBER) | (1L << STRING))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			case LIST_BEGIN:
				enterOuterAlt(_localctx, 2);
				{
				setState(49); list();
				}
				break;
			case FUNCTOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(50); structure();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public TerminalNode LIST_END() { return getToken(PrologParser.LIST_END, 0); }
		public List<TerminalNode> AND() { return getTokens(PrologParser.AND); }
		public TerminalNode LIST_BEGIN() { return getToken(PrologParser.LIST_BEGIN, 0); }
		public TerminalNode AND(int i) {
			return getToken(PrologParser.AND, i);
		}
		public TailContext tail() {
			return getRuleContext(TailContext.class,0);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); match(LIST_BEGIN);
			setState(65);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARIABLE) | (1L << FUNCTOR) | (1L << NUMBER) | (1L << STRING) | (1L << LIST_BEGIN))) != 0)) {
				{
				setState(54); argument();
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(55); match(AND);
					setState(56); argument();
					}
					}
					setState(61);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(63);
				_la = _input.LA(1);
				if (_la==LIST_LINE) {
					{
					setState(62); tail();
					}
				}

				}
			}

			setState(67); match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TailContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(PrologParser.VARIABLE, 0); }
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public TerminalNode LIST_LINE() { return getToken(PrologParser.LIST_LINE, 0); }
		public TailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).enterTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologListener ) ((PrologListener)listener).exitTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrologVisitor ) return ((PrologVisitor<? extends T>)visitor).visitTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TailContext tail() throws RecognitionException {
		TailContext _localctx = new TailContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tail);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); match(LIST_LINE);
			setState(72);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(70); match(VARIABLE);
				}
				break;
			case LIST_BEGIN:
				{
				setState(71); list();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\25M\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\7\2\20\n"+
		"\2\f\2\16\2\23\13\2\3\3\3\3\3\3\3\3\3\3\7\3\32\n\3\f\3\16\3\35\13\3\5"+
		"\3\37\n\3\3\3\3\3\5\3#\n\3\3\4\3\4\3\4\3\4\3\4\7\4*\n\4\f\4\16\4-\13\4"+
		"\3\4\3\4\5\4\61\n\4\3\5\3\5\3\5\5\5\66\n\5\3\6\3\6\3\6\3\6\7\6<\n\6\f"+
		"\6\16\6?\13\6\3\6\5\6B\n\6\5\6D\n\6\3\6\3\6\3\7\3\7\3\7\5\7K\n\7\3\7\2"+
		"\b\2\4\6\b\n\f\2\3\5\3\3\5\5\17\17R\2\21\3\2\2\2\4\24\3\2\2\2\6$\3\2\2"+
		"\2\b\65\3\2\2\2\n\67\3\2\2\2\fG\3\2\2\2\16\20\5\4\3\2\17\16\3\2\2\2\20"+
		"\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\3\3\2\2\2\23\21\3\2\2\2\24"+
		"\36\5\6\4\2\25\26\7\n\2\2\26\33\5\6\4\2\27\30\7\b\2\2\30\32\5\6\4\2\31"+
		"\27\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\37\3\2\2\2\35"+
		"\33\3\2\2\2\36\25\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 \"\7\7\2\2!#\7\25"+
		"\2\2\"!\3\2\2\2\"#\3\2\2\2#\5\3\2\2\2$\60\7\4\2\2%&\7\20\2\2&+\5\b\5\2"+
		"\'(\7\b\2\2(*\5\b\5\2)\'\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2"+
		"\2-+\3\2\2\2./\7\21\2\2/\61\3\2\2\2\60%\3\2\2\2\60\61\3\2\2\2\61\7\3\2"+
		"\2\2\62\66\t\2\2\2\63\66\5\n\6\2\64\66\5\6\4\2\65\62\3\2\2\2\65\63\3\2"+
		"\2\2\65\64\3\2\2\2\66\t\3\2\2\2\67C\7\22\2\28=\5\b\5\29:\7\b\2\2:<\5\b"+
		"\5\2;9\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>A\3\2\2\2?=\3\2\2\2@B\5\f"+
		"\7\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2\2C8\3\2\2\2CD\3\2\2\2DE\3\2\2\2EF\7\24"+
		"\2\2F\13\3\2\2\2GJ\7\23\2\2HK\7\3\2\2IK\5\n\6\2JH\3\2\2\2JI\3\2\2\2K\r"+
		"\3\2\2\2\r\21\33\36\"+\60\65=ACJ";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}