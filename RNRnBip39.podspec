require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "RNRnBip39"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  RNRnBip39
                   DESC
  s.homepage     = "https://github.com/potato-coin/rn-bip39"
  s.license      = "MIT"
  # s.license    = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author       = { "author" => "rise.worlds@outlook.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/potato-coin/rn-bip39.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"
  #s.dependency "others"
end

  