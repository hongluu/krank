package org.crank.crud.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries( {
	@NamedQuery(name="Employee.findEmployeesByDepartment",
			query="from Employee employee where employee.department.name=?"),
	@NamedQuery(name="Employee.readPopulated",
					query="select distinct employee from Employee employee join fetch employee.department " +
                            "left outer join fetch employee.tasks where employee.id=?"),
	@NamedQuery(name="Employee.findInEmployeeIds",
							query="SELECT o FROM Employee o  WHERE  o.id in  ( ? )"),
    @NamedQuery(name="Employee.findSalaryEmployees",
				query="SELECT o FROM Employee o  WHERE  o.status = org.crank.crud.model.EmployeeStatus.SALARY")
				,
	@NamedQuery(name="Employee.findExcellentEmployees",
						query="SELECT o FROM Employee o  WHERE  o.rank = org.crank.crud.model.EmployeeRank.EXCELLENT"
	)
	
})
public class Employee implements Serializable{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )	
	private Long id;

    @Column (nullable=false, length=32)
	private String firstName;
	
	private String lastName;
    
    @Column (length=150)
    private String description;
	
	private boolean active;
	
	private int age;
	
	private Integer numberOfPromotions;
	
	private EmployeeStatus status;
	
	private Integer rank;
	
    private Date dob;
    
    @OneToMany (cascade=CascadeType.ALL)    
    private Set<Task> tasks;

    //@ManyToOne(fetch=FetchType.LAZY)
    @ManyToOne()
	private Department department;
    
    public void addTask(Task task) {
        this.tasks.add( task );
    }
    public void removeTask(Task task) {
        this.tasks.remove( task );
    }
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
        System.out.println("SET DEPARTMENT " + department);
		this.department = department;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getNumberOfPromotions() {
		return numberOfPromotions;
	}

	public void setNumberOfPromotions(Integer numberOfPromotions) {
		this.numberOfPromotions = numberOfPromotions;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EmployeeStatus getStatus() {
		return status;
	}

	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

    public Date getDob() {
        return dob;
    }

    public void setDob( Date dob ) {
        this.dob = dob;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks( Set<Task> tasks ) {
        this.tasks = tasks;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription( String description ) {
        this.description = description;
    }
}
