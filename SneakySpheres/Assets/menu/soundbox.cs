using UnityEngine;
using System.Collections;

public class soundbox : MonoBehaviour {

	public int kistenArt;
	AudioSource audio;


	// Use this for initialization
	void Start()
	{
		audio = gameObject.GetComponent<AudioSource>();

	}


	public void playsound()
	{

		audio.Play();
	}


	void OnCollisionEnter(Collision col)
	{


		functionality();
		audio.Play();
		Destroy(col.gameObject);

	}    

	public  void functionality()
	{
		switch (kistenArt)
		{
		case 0:
			AudioListener.volume += 0.2f;
			break;

		case 1:
			AudioListener.volume -= 0.2f;
			break;
		}
	}
}
