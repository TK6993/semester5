using UnityEngine;
using System.Collections;

public class walls : MonoBehaviour {

    public GameObject player;
    AudioSource audio;
	// Use this for initialization
	void Start () {
       audio = gameObject.GetComponent<AudioSource>();
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnCollisionEnter(Collision col)
    {
		if (col.gameObject.Equals(player))
        {
            if(!audio.isPlaying) audio.Play();

        }
    }
}
