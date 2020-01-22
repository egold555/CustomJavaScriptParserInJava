
//var Sequence = org.golde.jstest.Sequence;
//var Channel = org.golde.jstest.Channel;
//var Event = org.golde.jstest.Event;
//var SelectedArea = org.golde.jstest.SelectedArea;

//var List = java.util.List;

var execute = function(instance, engine, classLoader){
	
//    var Random = java.util.Random;
//    var rand = new Random();
//    
//    for(var i = 0; i < 40; i++){
//        instance.log("Hello World! " + rand.nextInt(10) + " " + randInt(10));
//    }
//    
    
    instance.log(instance.getSequence().getChannels().size());
    
    var seq = instance.getSequence();
    var channels = seq.getChannels();
    instance.log(channels.get(0).getAllEvents() + " <---");
	

}

var randInt = function(n) {
    return Math.floor(Math.random() * n);
};