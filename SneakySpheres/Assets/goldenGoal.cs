using UnityEngine;
using System.Collections;

public class goldenGoal : MonoBehaviour {

    AudioSource audio;

	// Use this for initialization
	void Start () {
        audio = gameObject.GetComponent<AudioSource>();
	
	}

    public void playsound() {

        audio.Play();
    }
}
