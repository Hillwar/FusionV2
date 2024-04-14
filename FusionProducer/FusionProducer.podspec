Pod::Spec.new do |s|
  s.name             = 'FusionProducer'
  s.version          = '0.1.0'
  s.summary          = "Backend-Driven UI"
  s.description      = <<-DESC
                         Длинное описание.
                         DESC
  s.homepage         = 'https://github.com/Hillwar/FusionV2'
  s.license          = { :type => 'MIT', :file => 'LICENSE' }
  s.author           = 'Mikhailov Kirill'
  s.source           = { :path => '.' }
  s.source_files = 'Sources/**/*.{h,swift}'

  s.ios.deployment_target = '13.0'
  s.swift_version = '5.4'
  s.static_framework = true
  s.preserve_paths = '*'
  s.frameworks = 'UIKit', 'Foundation'
  s.weak_frameworks = 'JavaScriptCore', 'SwiftUI'
end
