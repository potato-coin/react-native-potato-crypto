require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "PotatoCrypto"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = "https://github.com/potato-coin/react-native-potato-crypto"
  s.license      = "MIT"
  s.author       = { "author" => "rise.worlds@outlook.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/potato-coin/react-native-potato-crypto.git", :tag => "#{s.version}" }

  s.ios.deployment_target = "8.0"
  s.tvos.deployment_target = "9.0"
  
  s.source_files = "ios/**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"
  #s.dependency "others"
end

  