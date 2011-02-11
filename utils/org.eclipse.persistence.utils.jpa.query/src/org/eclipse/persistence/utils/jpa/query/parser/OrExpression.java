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
 * <div nowrap><b>BNF:</b> <code>conditional_expression ::= conditional_expression OR conditional_term</code><p>
 *
 * @version 11.2.0
 * @since 11.0.0
 * @author Pascal Filion
 */
public final class OrExpression extends LogicalExpression
{
	/**
	 * Creates a new <code>OrExpression</code>.
	 *
	 * @param parent The parent of this expression
	 */
	OrExpression(AbstractExpression parent)
	{
		super(parent, OR);
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
	JPQLQueryBNF rightExpressionBNF()
	{
		return queryBNF(ConditionalTermBNF.ID);
	}
}