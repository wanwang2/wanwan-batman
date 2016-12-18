package org.wanwanframework.compiler;

public class Parser {

	enum NodeKind {
		None, 
		CLASS_K, 
		CLASS_VAR_DEC_K, 
		SUBROUTINE_DEC_K, 
		BASIC_TYPE_K, 
		CLASS_TYPE_K, 
		NULL_K, 
		PARAM_K, 
		VAR_DEC_K, 
		ARRAY_K, 
		VAR_K, 
		IF_STATEMENT_K, 
		WHILE_STATEMENT_K, 
		CALL_EXPRESSION_K, 
		RETURN_STATEMENT_K, 
		CALL_STATEMENT_K, 
		BOOL_EXPRESSION_K, 
		FUNCTION_CALL_K, 
		CONSTRUCTOR_CALL_K, 
		COMPARE_K, 
		OPERATION_K, 
		BOOL_K, 
		ASSIGN_K, 
		SUBROUTINE_BODY_K, 
		BOOL_CONST_K, 
		NEGATIVE_K, 
		METHOD_CALL_K, 
		INT_CONST_K, 
		CHAR_CONST_K, 
		STRING_CONST_K, 
		KEY_WORD_CONST, 
		THIS_K
	};
}
