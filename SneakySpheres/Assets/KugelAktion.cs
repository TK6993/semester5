using UnityEngine;
using System.Collections;

public class KugelAktion : MonoBehaviour
{
    float speed;
    public Transform kern;
    public AudioSource audio;
    public float velocity;
    public bool dead;
    public int winCounter;
    public Color color;
    public float changecolor;
    public Light thisLight;
    public GameObject Win;
    public GameObject lose;
    public GameObject nextL;
    // Use this for initialization
    void Start()
    {
        speed = 30.0f;
        audio = GetComponent<AudioSource>();
        velocity = gameObject.GetComponent<Rigidbody>().velocity.magnitude;
        winCounter = 0;
        dead = false;
        color = gameObject.GetComponent<MeshRenderer>().materials[0].color;
        float changecolor = 0;
    }

    // Update is called once per frame
    void Update()
    {
        steuerung();
        audio.volume = (velocity / 45);
		if (Input.GetKeyDown (KeyCode.Escape)) {
			Application.LoadLevel ("menu");
		}

        if (velocity<=1) {audio.Pause();}
        else { if (!audio.isPlaying) {audio.Play(); } }

        if (winCounter == 6 && changecolor <= 1) { winFunction(); }
        if (dead && changecolor<=1) { loseFunction(); }

    }




    void steuerung() {

        velocity = gameObject.GetComponent<Rigidbody>().velocity.magnitude;
        Vector3 left = kern.TransformDirection(Vector3.left);
        Vector3 forward = kern.TransformDirection(Vector3.forward);
        if (Input.GetKey("up")||Input.GetKey("w"))
        {
            gameObject.GetComponent<Rigidbody>().AddForce(forward * speed);
        }
        if (Input.GetKey("down") || Input.GetKey("s"))
        {
            gameObject.GetComponent<Rigidbody>().AddForce(forward * -speed);
        }
        if (Input.GetKey("left") || Input.GetKey("a"))
        {
            gameObject.GetComponent<Rigidbody>().AddForce(left * +speed);
        }
        if (Input.GetKey("right") || Input.GetKey("d"))
        {
            gameObject.GetComponent<Rigidbody>().AddForce(left * -speed);
        }

    }

    void winFunction() {
        gameObject.GetComponent<ParticleSystem>().Play();
        gameObject.GetComponent<MeshRenderer>().materials[0].color = Color.Lerp(color, Color.yellow, changecolor);
        thisLight.color = Color.Lerp(color, Color.yellow, changecolor);
        changecolor += 0.02f;
		if (Application.loadedLevelName.Equals ("3")) {Win.SetActive (true);}
		else {nextL.SetActive (true);}
        StartCoroutine(waitWin(4));
    }
    void loseFunction() {
        gameObject.GetComponent<ParticleSystem>().Play();
        gameObject.GetComponent<MeshRenderer>().materials[0].color = Color.Lerp(color, Color.red, changecolor);
        thisLight.color = Color.Lerp(color, Color.red, changecolor);
        changecolor += 0.02f;
        lose.SetActive(true);
        StartCoroutine(waitLose(4));
        
        
    }

    IEnumerator waitLose(int seconds)
    {
        yield return new WaitForSeconds(seconds);
		if(Application.loadedLevelName.Equals("1")) Application.LoadLevel("1");
		else if (Application.loadedLevelName.Equals("2")) Application.LoadLevel("2");
		else if (Application.loadedLevelName.Equals("3")) Application.LoadLevel("3");
    }

    IEnumerator waitWin(int seconds)
    {
        yield return new WaitForSeconds(seconds);
        if(Application.loadedLevelName.Equals("1")) Application.LoadLevel("2");
        else if (Application.loadedLevelName.Equals("2")) Application.LoadLevel("3");
        else if (Application.loadedLevelName.Equals("3")) Application.LoadLevel("menu");


    }
}