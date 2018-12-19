@Query sort
	@Query(value = "select * from `user`")
	List<User> findAll(Sort sort)
@Query 分页
	@Query(value = "select * from `user` /* #pageable# */",nativeQuery = true)
	Page<User> findAll(Pageable pageable)
		/* #pageable# */ 可能在低版本时这个必须有 而且sql还当做注释打印出来了
@NamedStoresProcedureQUery + @Procedure("")存储过程查询

	存储过程使用了注释@NamedStoredProcedureQuery， 并绑定到
	一个JPA表。
	procedureName是存储过程的名字。
	name是JPA中存储过程的名字。
	使用注释@StoredProcedureParameter来定义存储过程使用的
	IN/OUT参数。
	
	@Procedure的procedureName参数必须匹配
	@NamedStoredProcedureQuery的procedureName。
	@Procedure的name参数必须匹配@NamedStoredProcedureQuery
	的name。
	@Param必须匹配@StoredProcedureParameter注释的name参
	数。
	返回类型必须匹配： in_only_test存储过程返回是void，
	in_and_out_test存储过程必须返回String。

多表
	https://blog.csdn.net/sky786905664/article/details/52314353
	1:m
		SetJoin<UserModel,DepModel> depJoin = root.join(root.getModel().getSet("setDep",DepModel.class) , 		JoinType.LEFT);
		Predicate p4 = cb.equal(depJoin.get("name").as(String.class), "ddd"
		//把Predicate应用到CriteriaQuery去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥 的
		query.where(cb.and(cb.and(p3,cb.or(p1,p2)),p4));
		//添加分组的功能
		query.orderBy(cb.desc(root.get("uuid").as(Integer.class)));
		return query.getRestriction();
	1:1
		Join<UserModel,DepModel> depJoin =
		root.join(root.getModel().getSingularAttribute("dep",DepModel.class),JoinType.LEFT);
sql
   public class UserRepositoryImpl {  
    @PersistenceContext  
    private EntityManager em;     
    public Page<Object[]> getByCondition(UserQueryModel u){  
		String hql = "select o.uuid,o.name from UserModel o where 1=1 and o.uuid=:uuid";  
        Query q = em.createQuery(hql);  
        q.setParameter("uuid", u.getUuid());          
        q.setFirstResult(0);  
        q.setMaxResults(1);       
		Page<Object[]> page = new PageImpl<Object[]>(q.getResultList(),new PageRequest(0,1),3);   
        return page;  
	}}  

