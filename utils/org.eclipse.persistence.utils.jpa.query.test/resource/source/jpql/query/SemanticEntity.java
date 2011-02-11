package jpql.query;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries
({
	@NamedQuery(name="AbsExpression_InvalidExpression_1",                                 query="SELECT e FROM Employee e WHERE ABS(e.name)"),
	@NamedQuery(name="AbsExpression_InvalidExpression_2",                                 query="SELECT e FROM Employee e WHERE ABS(e.empId)"),
	@NamedQuery(name="AvgFunction_InvalidExpression_1",                                   query="SELECT e FROM Employee e WHERE AVG(e.name)"),
	@NamedQuery(name="AvgFunction_InvalidExpression_2",                                   query="SELECT e FROM Employee e WHERE AVG(e.empId)"),
	@NamedQuery(name="BetweenExpression_WrongType_1",                                     query="SELECT e FROM Employee e WHERE e.name BETWEEN 'A' AND 2"),
	@NamedQuery(name="BetweenExpression_WrongType_2",                                     query="SELECT e FROM Employee e WHERE e.empId BETWEEN 'A' AND 2"),
	@NamedQuery(name="BetweenExpression_WrongType_3",                                     query="SELECT e FROM Employee e WHERE e.empId BETWEEN 'A' AND 'Z'"),
	@NamedQuery(name="BetweenExpression_WrongType_4",                                     query="SELECT e FROM Employee e WHERE e.name BETWEEN 'A' AND :name"),
	@NamedQuery(name="CollectionMemberDeclaration_InvalidCollectionExpression_1",         query="SELECT a FROM Address a, IN(a.zip) c"),
	@NamedQuery(name="CollectionMemberDeclaration_InvalidCollectionExpression_2",         query="SELECT a FROM Address a, IN(a.customerList) c"),
	@NamedQuery(name="CollectionMemberExpression_Embeddable_1",                           query="SELECT a FROM Address a, Customer c WHERE c MEMBER OF a.customerList"),
	@NamedQuery(name="CollectionMemberExpression_Embeddable_2",                           query="SELECT a FROM Address a, Customer c WHERE a.zip MEMBER OF a.customerList"),
	@NamedQuery(name="CollectionMemberExpression_InvalidCollectionExpression_1",          query="SELECT a FROM Address a, Customer c WHERE c MEMBER OF a.customerList"),
	@NamedQuery(name="CollectionMemberExpression_InvalidCollectionExpression_2",          query="SELECT a FROM Address a, Customer c WHERE c MEMBER OF a.state"),
	@NamedQuery(name="ComparisonExpression_WrongComparisonType_1",                        query="SELECT e FROM Employee e WHERE NULLIF(e.working, TRUE) < LENGTH(e.name) + 2"),
	@NamedQuery(name="ComparisonExpression_WrongComparisonType_2",                        query="SELECT e FROM Employee e WHERE COALESCE(e.working, :name) < LENGTH(e.name)"),
	@NamedQuery(name="ComparisonExpression_WrongComparisonType_3",                        query="SELECT e FROM Employee e WHERE ALL(SELECT p.id FROM Project p) < e.salary"),
	@NamedQuery(name="ComparisonExpression_WrongComparisonType_4",                        query="SELECT e FROM Employee e WHERE ALL(SELECT p.id FROM Project p) < e.name"),
	@NamedQuery(name="ComparisonExpression_WrongComparisonType_5",                        query="SELECT e FROM Employee e WHERE ALL(SELECT p.id FROM Project p) < (e.name)"),
	@NamedQuery(name="ComparisonExpression_WrongComparisonType_6",                        query="SELECT e FROM Employee e WHERE ALL(SELECT p.id FROM Project p) < (e.salary)"),
	@NamedQuery(name="ComparisonExpression_WrongComparisonType_7",                        query="SELECT e FROM Employee e WHERE ALL < e.salary"),
	@NamedQuery(name="ConstructorExpression_UnknownType_1",                               query="SELECT NEW Address(e.name) FROM Employee e"),
	@NamedQuery(name="ConstructorExpression_UnknownType_2",                               query="SELECT NEW String(e.name) FROM Employee e"),
	@NamedQuery(name="ConstructorExpression_UnknownType_3",                               query="SELECT NEW jpql.query.Employee(e.name) FROM Employee e"),
	@NamedQuery(name="ConcatExpression_FirstExpression_WrongType_1",                      query="SELECT e FROM Employee e WHERE CONCAT('JPQL', 'query') = 'JPQL query'"),
	@NamedQuery(name="ConcatExpression_FirstExpression_WrongType_2",                      query="SELECT e FROM Employee e WHERE CONCAT(e.name, 'query') = 'JPQL query'"),
	@NamedQuery(name="ConcatExpression_FirstExpression_WrongType_3",                      query="SELECT e FROM Employee e WHERE CONCAT(e.empId, 'query') = 'JPQL query'"),
	@NamedQuery(name="ConcatExpression_SecondExpression_WrongType_1",                     query="SELECT e FROM Employee e WHERE CONCAT('JPQL', 'query') = 'JPQL query'"),
	@NamedQuery(name="ConcatExpression_SecondExpression_WrongType_2",                     query="SELECT e FROM Employee e WHERE CONCAT('JPQL', e.name) = 'JPQL query'"),
	@NamedQuery(name="ConcatExpression_SecondExpression_WrongType_3",                     query="SELECT e FROM Employee e WHERE CONCAT('JPQL', e.empId) = 'JPQL query'"),
	@NamedQuery(name="CountFunction_DistinctEmbeddable",                                  query="SELECT COUNT(DISTINCT a.zip) FROM Address a"),
	@NamedQuery(name="EmptyCollectionComparisonExpression_InvalidCollectionExpression_1", query="SELECT a FROM Address a WHERE a.state IS NOT EMPTY"),
	@NamedQuery(name="EmptyCollectionComparisonExpression_InvalidCollectionExpression_2", query="SELECT a FROM Address a WHERE a.customerList IS NOT EMPTY"),
	@NamedQuery(name="EncapsulatedIdentificationVariableExpression_NotMapValued_1",       query="SELECT ENTRY(cust) FROM CodeAssist c JOIN c.customerMap cust"),
	@NamedQuery(name="EncapsulatedIdentificationVariableExpression_NotMapValued_2",       query="SELECT ENTRY(c) FROM Address a JOIN a.customerList c"),
	@NamedQuery(name="IdentificationVariable_EntityName",                                 query="SELECT Employee FROM Address Employee"),
	@NamedQuery(name="InExpression_InItem_WrongType_1",                                   query="SELECT e FROM Employee e WHERE e.name IN(e, 'JPQL')"),
	@NamedQuery(name="InExpression_InItem_WrongType_2",                                   query="SELECT e FROM Employee e WHERE e.name IN(e.name, :name, 'JPQL')"),
	@NamedQuery(name="InExpression_WrongType_1",                                          query="SELECT e FROM Employee e WHERE e.name IN(e.empId)"),
	@NamedQuery(name="InExpression_WrongType_2",                                          query="SELECT e FROM Employee e WHERE e.name IN('JPQL', 'Java')"),
	@NamedQuery(name="InExpression_WrongType_3",                                          query="SELECT e FROM Employee e WHERE e.name IN(?1, ?2, ?3)"),
	@NamedQuery(name="LengthExpression_WrongType_1",                                      query="SELECT e FROM Employee e WHERE LENGTH(e.empId) = 2"),
	@NamedQuery(name="LengthExpression_WrongType_2",                                      query="SELECT e FROM Employee e WHERE LENGTH(e.name) = 2"),
	@NamedQuery(name="LowerExpression_WrongType_1",                                       query="SELECT e FROM Employee e WHERE LOWER('JPQL') = 'jpql'"),
	@NamedQuery(name="LowerExpression_WrongType_2",                                       query="SELECT e FROM Employee e WHERE LOWER(e.name) = 'jpql'"),
	@NamedQuery(name="LowerExpression_WrongType_3",                                       query="SELECT e FROM Employee e WHERE LOWER(e.empId) = 'jpql'"),
	@NamedQuery(name="ModExpression_FirstExpression_WrongType_1",                         query="SELECT e FROM Employee e WHERE MOD(e.name, 3) = 1"),
	@NamedQuery(name="ModExpression_FirstExpression_WrongType_2",                         query="SELECT d FROM Dept d WHERE MOD(d.floorNumber, 3) = 1"),
	@NamedQuery(name="ModExpression_FirstExpression_WrongType_3",                         query="SELECT e FROM Employee e, Product p WHERE MOD(p.quantity, 3) = 1"),
	@NamedQuery(name="ModExpression_SecondExpression_WrongType_1",                        query="SELECT e FROM Employee e WHERE MOD(3, e.name) = 1"),
	@NamedQuery(name="ModExpression_SecondExpression_WrongType_2",                        query="SELECT d FROM Dept d WHERE MOD(3, d.floorNumber) = 1"),
	@NamedQuery(name="ModExpression_SecondExpression_WrongType_3",                        query="SELECT d FROM Dept d, Product p WHERE MOD(3, p.quantity + d.floorNumber) = 1"),
	@NamedQuery(name="NullComparisonExpression_InvalidCollectionExpression_1",            query="SELECT a FROM Address a WHERE a.zip IS NOT NULL"),
	@NamedQuery(name="NullComparisonExpression_InvalidCollectionExpression_2",            query="SELECT a FROM Address a WHERE a.customerList IS NOT NULL"),
	@NamedQuery(name="SelectStatement_SelectClauseHasNonAggregateFunctions_1",            query="SELECT e FROM Employee e HAVING COUNT(e) >= 5"),
	@NamedQuery(name="SelectStatement_SelectClauseHasNonAggregateFunctions_2",            query="SELECT AVG(e.age), e FROM Employee e HAVING COUNT(e) >= 5"),
	@NamedQuery(name="SelectStatement_SelectClauseHasNonAggregateFunctions_3",            query="SELECT AVG(e.age), COUNT(e) FROM Employee e HAVING COUNT(e) >= 5"),
	@NamedQuery(name="SizeExpression_InvalidCollectionExpression_1",                      query="SELECT a FROM Address a WHERE SIZE(a.customerList) = 2"),
	@NamedQuery(name="SizeExpression_InvalidCollectionExpression_2",                      query="SELECT a FROM Address a WHERE SIZE(a.zip) = 2"),
	@NamedQuery(name="SqrtExpression_WrongType_1",                                        query="SELECT a FROM Address a WHERE SQRT(a.zip) = 2"),
	@NamedQuery(name="SqrtExpression_WrongType_2",                                        query="SELECT e FROM Employee e WHERE SQRT(e.salary) = 2"),
	@NamedQuery(name="SqrtExpression_WrongType_3",                                        query="SELECT e FROM Employee e WHERE SQRT(NULLIF(e.name, :name)) = 2"),
	@NamedQuery(name="SqrtExpression_WrongType_4",                                        query="SELECT e FROM Employee e WHERE SQRT(NULLIF(e.salary, :salary)) = 2"),
	@NamedQuery(name="SubstringExpression_FirstExpression_WrongType_1",                   query="SELECT e FROM Employee e WHERE SUBSTRING(e.empId, 1, 2) = 'P'"),
	@NamedQuery(name="SubstringExpression_FirstExpression_WrongType_2",                   query="SELECT e FROM Employee e WHERE SUBSTRING(e.name, 1, 2) = 'P'"),
	@NamedQuery(name="SubstringExpression_SecondExpression_WrongType_1",                  query="SELECT e FROM Employee e WHERE SUBSTRING(e.name, 2 + e.name, 2) = 'P'"),
	@NamedQuery(name="SubstringExpression_SecondExpression_WrongType_2",                  query="SELECT e FROM Employee e WHERE SUBSTRING(e.name, 1, 2) = 'P'"),
	@NamedQuery(name="SubstringExpression_ThirdExpression_WrongType_1",                   query="SELECT e FROM Employee e WHERE SUBSTRING(e.name, 2, 2 + e.name) = 'P'"),
	@NamedQuery(name="SubstringExpression_ThirdExpression_WrongType_2",                   query="SELECT e FROM Employee e WHERE SUBSTRING(e.name, 1, 2) = 'P'"),
	@NamedQuery(name="UpperExpression_WrongType_1",                                       query="SELECT e FROM Employee e WHERE UPPER('jpql') = 'JPQL'"),
	@NamedQuery(name="UpperExpression_WrongType_2",                                       query="SELECT e FROM Employee e WHERE UPPER(e.name) = 'JPQL'"),
	@NamedQuery(name="UpperExpression_WrongType_3",                                       query="SELECT e FROM Employee e WHERE UPPER(e.empId) = 'JPQL'")
})
public final class SemanticEntity
{
}