# Animator Project
 
<h3>Overview of Design</h3>

The main feature of this project is the model Animator interface 
implemented by the ShapeAnimator class. Animator has the functionality
to Add and remove shapes to the animation, and add and remove motions
of these shapes over time.

This is done by using the AnimatedShape interface as a representation
of a single shape and all of its movements throughout the animation.
Each AnimatedShape contains Shapes which are representations of
a shape at one point in time as well as motions which are the
interactions between these Shapes.

Each Shape has a position, width, height, and a color.

This project also contains a AnimationView that is implemented
as a text view which displays all shapes in the animation and
their motions.

<h3>Structure</h3>
![img.png](img.png)

<h3>Classes Overview</h3>

Shape: Interface to represent a shape that has a position, width,
height, and color. This is meant to be immutable and hold 
information solely for a shape at one point in time in the animation.

AbstractShape: Abstract implementation of Shape that simply holds
variables for position, width, height, and color and implements
basic shape functions.

Rectangle & Ellipse: Concrete implementations of AbstractShape
that currently have no difference than name and ShapeType.

AnimatorState: This is a state representation of the model that
only has the ability to get information about an animation, not 
mutate. This is used by the view.

Animator: Extension of the AnimatorState that adds the ability
to modify and add shapes and motions to the animation as described
in the documentation.

ShapeAnimator: Implementation of Animator which uses AnimatedShapes and 
ShapeID strings in key value pairs to hold information about the shapes.

AnimatedShape: Represents a specific shape throughout an animation.

SimpleAnimatedShape: Implementation of AnimatedShape.

AnimatorView: Interface to view the model with a render method.

AnimatorTextView: Implementation of AnimatorView that uses text
to display a representation of an animation.

Color: Representation of a color with red, green, and blue values.

Position: Representation of a position on a canvas with an x and y
value.

Motion: Representation of a motion of a shape that has a start time, 
and end time, and an end shape. Throughout the motion, the shape will
continuously transform to the end shape. 

ShapeType: Enum which represents the types of shapes that can be
made in the animation. Currently, this only holds Rectangle and Ellipse.

AnimationRunner: Class with a main method used for manual testing.

Utility: class with static methods that are generally useful
throughout the program.