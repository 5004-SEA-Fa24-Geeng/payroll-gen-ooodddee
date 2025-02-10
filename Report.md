# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for?

    CSV stands for Comma-Separated Values.


2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

   `List<IEmployee>` lets you store both `HourlyEmployee` and `SalaryEmployee`, while `ArrayList<HourlyEmployee>` can only store hourly employees. It also makes the code easier to change later.


3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?
   
    This is a has-a relationship because one class has another class as an attribute. For example, a PayStub has an IEmployee.


4. Can you provide an example of a has-a relationship in your code (if one exists)?

    `PayStub` has an `IEmployee` as an attribute


5. Can you provide an example of an is-a relationship in your code (if one exists)?

    `HourlyEmployee` and `SalaryEmployee` inherit from `EmployeeBigDecimal`


6. What is the difference between an interface and an abstract class?

    An interface only has method names (no code inside), and a class can use multiple interfaces. An abstract class can have both method names and some code, but a class can only extend one abstract class.


7. What is the advantage of using an interface over an abstract class?

   An interface is more flexible because a class can implement multiple interfaces, but it can only extend one abstract class.


8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 

   No, that code is not valid in Java because generics only work with reference types, not primitive types like int
    ```
   List<Integer> numbers = new ArrayList<>();
   ```


9. Which class/method is described as the "driver" for your application? 

   There isn’t a class with a `main` method, so there is no actual “driver” in this code.


10. How do you create a temporary folder for JUnit Testing? 

Use JUnit’s @TempDir annotation to inject a temporary folder
```
@TempDir
Path tempDir;
```


## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 


> We can add extra information in the payroll system—like each employee’s gender or ethnicity—to compare salaries across groups. For example, the system could create automatic reports each pay period to spot any pay gaps. Research shows women in many industries, including STEM, often earn less (AAUW, 2023), and companies benefit from a more diverse workforce (McKinsey & Company, 2022). By integrating these data points into payroll, we can help ensure fairer salaries.
<https://www.mckinsey.com/featured-insights/diversity-and-inclusion/women-in-the-workplace>
<https://www.aauw.org/resources/research/simple-truth/>