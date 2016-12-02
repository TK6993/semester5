using UnityEngine;
using System.Collections;

public class setMouse : MonoBehaviour {

    public Texture2D[] cursorTextures;
    public int rate;
    int spriteSize;
    int activeText;
    int counter;
    bool goDown;

    // Use this for initialization
    void Start() {
        Cursor.SetCursor(cursorTextures[0], new Vector2(cursorTextures[0].height / 2, cursorTextures[0].width / 2), CursorMode.ForceSoftware);
        spriteSize = cursorTextures.Length;
        activeText = 0;
        bool goDown = false;
    }
	
	// Update is called once per frame
	void Update() {
        counter++;
        if (counter >= rate) {
            counter = 0;
            if (activeText == spriteSize-1) goDown = true;
            if (activeText == 0) goDown = false;
            Cursor.SetCursor(cursorTextures[activeText], new Vector2(cursorTextures[activeText].height / 2, cursorTextures[activeText].width / 2), CursorMode.ForceSoftware);
            if (goDown) activeText--;
            else { activeText++; }


        }

    }
}
