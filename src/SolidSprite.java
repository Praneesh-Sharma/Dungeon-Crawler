import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite{
    // Constructor remains the same
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    // Override getHitBox to allow a smaller bounding box for collision detection
//    @Override
    public Rectangle2D getHitBox() {
        // You can shrink the hitbox here if needed, like reducing width and height by a factor.
        double shrinkFactor = 0.67; // Shrink to 80% of the original size

        // Apply shrinking to the width and height
        double newWidth = this.width * shrinkFactor;
        double newHeight = this.height * shrinkFactor;

        // Center the new hitbox around the original position
        double newX = this.x + (this.width - newWidth) / 2;
        double newY = this.y + (this.height - newHeight) / 2;

        // Return the smaller, centered hitbox
        return new Rectangle2D.Double(newX, newY, newWidth, newHeight);
    }

    // Display tge hitboxes of the obstacles
//    public void draw(Graphics g) {
//        super.draw(g);  // Draw the sprite image
//
//        // Draw the hitbox (as a black square)
//        Rectangle2D hitBox = getHitBox();
//        g.setColor(Color.BLACK); // Set the color to black
//        g.fillRect((int) hitBox.getX(), (int) hitBox.getY(), (int) hitBox.getWidth(), (int) hitBox.getHeight());
//    }

    // Collision check with another hitbox
    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }
}
