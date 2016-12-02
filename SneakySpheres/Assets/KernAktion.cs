using UnityEngine;
using System.Collections;

public class KernAktion : MonoBehaviour {

    float rotation;
    float roatationSpeed;
	// Use this for initialization
	void Start () {
        rotation = 0;
        roatationSpeed = 3.0f;
	
	}
	
	// Update is called once per frame
	void Update () {
        
        
        if (rotation >= 360) { rotation = 0; }
        transform.eulerAngles = new Vector3(0, rotation, 0);

        if (Input.GetKey("right")|| Input.GetKey("d")) { rotation += roatationSpeed;}
        if (Input.GetKey("left")|| Input.GetKey("a")) { rotation -= roatationSpeed;}
    }
}
