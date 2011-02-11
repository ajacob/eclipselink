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
 * One of the aggregate functions. The arguments must be numeric.
 * <p>
 * <b>SUM</b> returns <code>Long</code> when applied to state-fields of integral
 * types (other than <code>BigInteger</code>); <code>Double</code> when applied
 * to state-fields of floating point types; <code>BigInteger</code> when applied
 * to state-fields of type <code>BigInteger</code>; and <code>BigDecimal</code>
 * when applied to state-fields of type <code>BigDecimal</code>. If <b>SUM</b>,
 * <b>AVG</b>, <b>MAX</b>, or <b>MIN</b> is used, and there are no values to
 * which the aggregate function can be applied, the result of the aggregate
 * function is <code>NULL</code>. If <code>COUNT</code> is used, and there are
 * no values to which <b>COUNT</b> can be applied, the result of the aggregate
 * function is 0.
 * <p>
 * <div nowrap><b>BNF:</b> <code>expression ::= SUM([DISTINCT] state_field_path_expression)</code><p>
 *
 * @version 11.2.0
 * @since 11.0.0
 * @author Pascal Filion
 */
public final class SumFunction extends AggregateFunction
{
	/**
	 * Creates a new <code>SumFunction</code>.
	 *
	 * @param parent The parent of this expression
	 */
	SumFunction(AbstractExpression parent)
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
	String parseIdentifier(WordParser wordParser)
	{
		return SUM;
	}
}