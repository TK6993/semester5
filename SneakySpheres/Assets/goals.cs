using UnityEngine;
using System.Collections;

public class goals : MonoBehaviour {
    public GameObject player;
    public Light illu;
    public goldenGoal parentScript;
	// Use this for initialization
	void Start () {
        parentScript = gameObject.GetComponentInParent<goldenGoal>();
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnCollisionEnter(Collision col) {
        if (col.gameObject == player) {
            illu.intensity = 8;
            player.GetComponent<KugelAktion>().winCounter++;
            parentScript.playsound();
           Destroy(gameObject); 
              
        }
    }
}
