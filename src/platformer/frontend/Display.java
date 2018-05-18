package platformer.frontend;

import java.nio.file.Path;

public interface Display {
    void drawText(int x, int y, String text);

    void drawRectangle(int x, int y, int width, int height);

    Texture loadTexture(Path p);
}
