using UnityEngine;
using System.Collections;

public class MenuKisten : MonoBehaviour {

    public Light illu;
    public goldenMenue parentScript;

    // Use this for initialization
    void Start()
    {
        parentScript = gameObject.GetComponentInParent<goldenMenue>();

    }


    void OnCollisionEnter(Collision col)
    {
        

            illu.intensity = 8;
            parentScript.playsound();
            parentScript.functionality();
            Destroy(gameObject);
        }    

    
}