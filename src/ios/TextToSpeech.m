#import "TextToSpeech.h"

@implementation TextToSpeech

@synthesize synthesizer;

- (void)pluginInitialize
{
    self.synthesizer = [[AVSpeechSynthesizer alloc]init];
}

- (void)speak:(CDVInvokedUrlCommand*)command
{
    NSString* text = [command.arguments objectAtIndex:0];

    AVSpeechUtterance *utterance = [AVSpeechUtterance speechUtteranceWithString:text];
    [utterance setRate:0.3f];
    [self.synthesizer speakUtterance:utterance];
}

@end
