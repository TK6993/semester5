using UnityEngine;
using System.Collections;

public class Gegner : MonoBehaviour {
    public float hardness;
    public GameObject player;
    AudioSource audio;

    Light ilu;

	// Use this for initialization
	void Start () {
        if (player != null)
        {
            ilu = player.GetComponentInChildren<Light>();
        }
        audio = gameObject.GetComponent<AudioSource>();
	}

    // Update is called once per frame
    void Update() {
        if(player != null)
        gameObject.GetComponent<Rigidbody>().AddForce(Vector3.Normalize (player.transform.position - gameObject.transform.position)*hardness);
        
	}


    void OnCollisionEnter(Collision col) {
        if (col.gameObject == player)
        {
            player.GetComponent<KugelAktion>().dead = true;
            audio.Play();
        }
       
    }
}
