/*******************************************************************************
 * Copyright (c) 1998, 2010 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available athttp://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle
 *
 ******************************************************************************/
package org.eclipse.persistence.utils.jpa.query.parser;

/**
 * An <b>EXISTS</b> expression is a predicate that is <code>true</code> only if
 * the result of the subquery consists of one or more values and that is <code>false</code>
 * otherwise.
 * <p>
 * <div nowrap><b>BNF:</b> <code>exists_expression ::= [NOT] EXISTS(subquery)</code><p>
 *
 * @version 11.2.0
 * @since 11.0.0
 * @author Pascal Filion
 */
public final class ExistsExpression extends AbstractSingleEncapsulatedExpression
{
	/**
	 * Creates a new <code>ExistsExpression</code>.
	 *
	 * @param parent The parent of this expression
	 */
	ExistsExpression(AbstractExpression parent)
	{
		super(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ExpressionVisitor visitor)
	{
		visitor.visit(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	JPQLQueryBNF encapsulatedExpressionBNF()
	{
		return queryBNF(SubQueryBNF.ID);
	}

	/**
	 * Returns the identifier this expression represents.
	 *
	 * @return Either <code>NOT_EXISTS</code> or <code>EXISTS</code>
	 */
	public String getIdentifier()
	{
		return hasNot() ? NOT_EXISTS : EXISTS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	JPQLQueryBNF getQueryBNF()
	{
		return queryBNF(ExistsExpressionBNF.ID);
	}

	/**
	 * Determines whether the identifier <b>NOT</b> was parsed.
	 *
	 * @return <code>true</code> if the identifier <b>NOT</b> was parsed;
	 * <code>false</code> otherwise
	 */
	public boolean hasNot()
	{
		return getText().startsWith(NOT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	AbstractExpression parse(WordParser wordParser,
	                         JPQLQueryBNF queryBNF,
	                         boolean tolerant)
	{
		if (tolerant)
		{
			return super.parse(wordParser, queryBNF, tolerant);
		}

		SimpleSelectStatement expression = new SimpleSelectStatement(this);
		expression.parse(wordParser, tolerant);
		return expression;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	String parseIdentifier(WordParser wordParser)
	{
		if (wordParser.startsWithIgnoreCase('N'))
		{
			return NOT_EXISTS;
		}

		return EXISTS;
	}
}