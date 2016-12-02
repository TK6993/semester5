using UnityEngine;
using System.Collections;

public class NewBehaviourScript : MonoBehaviour {

    // Use this for initialization

    public GameObject influcence;
    public int counter;
    public int rate;
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        if (counter >= rate) {
            Instantiate(influcence, this.transform.position, Quaternion.identity);
        }
	
	}
}
