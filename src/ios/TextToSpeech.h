#import <Cordova/CDVPlugin.h>
#import <AVFoundation/AVFoundation.h>

@interface TextToSpeech : CDVPlugin {}

- (void)speak:(CDVInvokedUrlCommand*)command;

@property (nonatomic, strong) AVSpeechSynthesizer *synthesizer;
@end
