# 🏭 Equation Generator Java v4.0

This is a program in Java that generates equations in the format <code>a<sub>n</sub>x<sup>n</sup> + a<sub>n-1</sub>x<sup>n-1</sup> + a<sub>n-2</sub>x<sup>n-2</sup> + ... + a<sub>1</sub>x + a<sub>0</sub> = 0</code> according to the power and others parameters sent.
  
## ⚙️ Used Technologies
<section style="flex-basis: 48%">
  <img align="center" alt="Java" height="45" width="60" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg">
  <img align="center" alt="JUnit 5" height="90" width="80" src="https://raw.githubusercontent.com/devicons/devicon/refs/heads/master/icons/junit/junit-original-wordmark.svg">
</section>

## 📋 How to use 

<p>
In a file, import <code>EquationGenerator</code> class of the <code>main.generator</code> package and create an its object. 
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
      <code>rootInterval:</code> <code>RootInterval</code> type object that receives the minimum and maximum that root's value can assume.
    </li>
    <li>
      <code>coefficientAInterval:</code> <code>CoefficientAInterval</code> type object that receives the minimum and maximum that value of coefficient A can assume. 
    </li>
    <li>
      <code>repeatedRoots:</code> if a equation can have repeated roots.
    </li>
  </ol>
</p>

<p>
  The return of this method will be an object of the <code>Equation</code> class.
</p>
