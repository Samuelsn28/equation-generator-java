# 🏭 Equation Generator Java

This is a program in Java that generates equations in the format <code>a<sub>n</sub>x<sup>n</sup> + a<sub>n-1</sub>x<sup>n-1</sup> + a<sub>n-2</sub>x<sup>n-2</sup> + ... + a<sub>1</sub>x + a<sub>0</sub> = 0</code> according to the power and others parameters sent.
  
## ⚙️ Used Technologies

<img align="center" alt="Java" height="45" width="60" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg">

## 📋 How to use 

<p>
In a file, import <code>EquationGenerator</code> class of the <code>generator</code> package and create an its object. 
</p>

<p>
  Use the <code>generateEquation</code> method and pass to it the parameters:
  <ol>
    <li>
      <code>degree:</code> The desired degree of the equation.</li>
    <li>
      <code>variableSign:</code> The desired sign of the unknowns of the equation.
    </li>
    <li>
      <code>minValueRoot:</code> minimum value that a root of the equation can assume.
    </li>
    <li>
      <code>maxValueRoot:</code> max value that a root of the equation can assume.
    </li>
    <li>
      <code>repeatedRoots:</code> if a equation can have repeated roots.
    </li>
  </ol>
</p>

<p>
  The return of this method will be an object of the <code>Equation</code> class.
</p>
